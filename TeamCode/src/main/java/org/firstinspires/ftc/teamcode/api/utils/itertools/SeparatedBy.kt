package org.firstinspires.ftc.teamcode.api.utils.itertools

fun <T> Iterator<T>.separatedBy(separator: T): Iterator<T> = SeparatedBy(this, separator)

class SeparatedBy<T>(private val inner: Iterator<T>, private val separator: T) : Iterator<T> {
    /**
     * True is a separator will be returned next, else false.
     * This may only be true if `[inner].hasNext()` is also true.
     */
    private var separatorNext: Boolean = false

    override fun hasNext() = if (separatorNext) {
        true
    } else {
        inner.hasNext()
    }

    override fun next(): T {
        return if (separatorNext) {
            separatorNext = false
            separator
        } else {
            val res = inner.next()

            if (inner.hasNext()) {
                separatorNext = true
            }

            res
        }
    }
}
