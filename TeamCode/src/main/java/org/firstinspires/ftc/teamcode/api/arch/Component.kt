package org.firstinspires.ftc.teamcode.api.arch

open class Component {
    open val pre: ((Context) -> Unit)? = null
    open val cycle: ((Context) -> Unit)? = null
    open val post: ((Context) -> Unit)? = null
}
