package io.github.landrynorris.sample

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.landrynorris.jni.sample.DataClass
import io.github.landrynorris.jni.sample.DataHolder
import io.github.landrynorris.jni.sample.SharedClass
import io.github.landrynorris.sample.ui.theme.JniUtilsTheme

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
            if(dataClass.doubles != doubles) error("Double array wasn't set correctly")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JniUtilsTheme {
        Greeting("Android")
    }
}