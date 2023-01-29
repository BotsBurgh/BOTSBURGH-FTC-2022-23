package org.firstinspires.ftc.teamcode.api.utils.itertools

fun <T> Iterator<T>.map(func: (T) -> T): Iterator<T> = Map(this, func)

class Map<T>(private val inner: Iterator<T>, private val func: (T) -> T): Iterator<T> {
    override fun hasNext(): Boolean = inner.hasNext()

    override fun next(): T = func(inner.next())
}
