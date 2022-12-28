package org.firstinspires.ftc.teamcode.api.arch

abstract class Component {
    open val pre: CtxFunc? = null
    open val cycle: CtxFunc? = null

    @Deprecated("The post phase has never worked in a standard TeleOp.", level = DeprecationLevel.ERROR)
    open val post: CtxFunc? = null

    open val order: Byte = DEFAULT_ORDER

    /**
     * Determines when the component should be run.
     *
     * [RunMode.Autonomous] means run only on autonomous, same likewise for [RunMode.TeleOp].
     * `null` specifies that the component will always be run. You can only disable the component by not registering it.
     */
    open val runMode: RunMode? = null
}
