package io.github.landrynorris.sample

/**
 * This class is a thin wrapper that calls our JNI code
 */
class ButtonRepository {
    private var ptr: Long = 0

    init {
        open()
        println("Pointer is $ptr")
    }

    private fun open() {
        ptr = JniBridge.createRepository()
    }

    fun buttonClicked() {
        JniBridge.buttonClicked(ptr)
    }

    fun getText(): String {
        return JniBridge.getText(ptr)
    }
}
