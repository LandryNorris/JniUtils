package io.github.landrynorris.jni.sample

class ButtonClickRepository {
    private var counter = 0

    fun clicked() {
        counter++
    }

    fun getText() = "Button was clicked $counter times"

    fun getCounter() = counter
}