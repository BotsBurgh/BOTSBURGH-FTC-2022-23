package org.firstinspires.ftc.teamcode.api.arch

open class Component {
    open val order: Byte = 0

    open val pre: ((ctx: Context) -> Unit)? = null
    open val cycle: ((ctx: Context) -> Unit)? = null
    open val post: ((ctx: Context) -> Unit)? = null
}
