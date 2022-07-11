package io.github.landrynorris.jniutils

data class Signature(val parameterClasses: List<JClass>, val returnClass: JClass) {
    override fun toString(): String {
        return "(${parameterClasses.joinToString(separator = "")})$returnClass"
    }
}
