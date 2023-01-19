package io.github.landrynorris.sample

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.github.landrynorris.jni.sample.DataClass
import io.github.landrynorris.jni.sample.DataHolder
import io.github.landrynorris.jni.sample.SharedClass

class MainActivity: AppCompatActivity() {
    private val buttonRepository = ButtonRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testJni()

        val label = findViewById<TextView>(R.id.text)

        findViewById<Button>(R.id.button).setOnClickListener {
            buttonRepository.buttonClicked()
            label.text = buttonRepository.getText()
        }

        findViewById<Button>(R.id.btnCrash).setOnClickListener {
            JniBridge.crash("App requested a Fatal Error")
        }

        findViewById<TextView>(R.id.doubleValuesLabel).text = getDoubleValuesText()
        findViewById<TextView>(R.id.doubleValuesLabel).text = JniBridge.handleShared(
            SharedClass(0.5, DataHolder(5, 2.5, "multiplied values"))
        )
    }

    private fun getDoubleValuesText(): String {
        return JniBridge.doubleAll(doubleArrayOf(0.0, 0.5, 2.0, -3.0)).joinToString(", ")
    }

    private fun testJni() {
        JniBridge.methodWithParameters(50)
        JniBridge.callJavaFunction(12.5)

        val didThrowException = JniBridge.signatureWithCType("Something")
        if(!didThrowException) error("No error when trying to get type of CPointer")

        val s = "A string"
        val i = 200
        val d = 256.5
        val doubles = doubleArrayOf(1.0, 0.0, -1.0, 1000.0)
        val dataClass = JniBridge.createDataClass(s, i, d, doubles)
        println("Data class is $dataClass")

        if(dataClass is DataClass) {
            if(dataClass.s != s) error("String wasn't set correctly")
            if(dataClass.i != i) error("Int wasn't set correctly")
            if(dataClass.d != d) error("Double wasn't set correctly")
            if(!dataClass.doubles.contentEquals(doubles)) error("Double array wasn't set correctly")
        }

        val dataClassWithNull = JniBridge.createDataClass(null, 0, 0.0, doubleArrayOf())
        if(dataClassWithNull is DataClass) {
            if(dataClassWithNull.s != null) error("String wasn't set correctly")
            if(dataClassWithNull.i != 0) error("Int wasn't set correctly")
            if(dataClassWithNull.d != 0.0) error("Double wasn't set correctly")
            if(dataClassWithNull.doubles.isNotEmpty()) error("Double array wasn't set correctly")
        }
    }
}
