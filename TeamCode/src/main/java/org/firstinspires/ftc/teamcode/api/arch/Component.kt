package org.firstinspires.ftc.teamcode.api.arch

/**
 * An open class that all components should extend.
 */
open class Component {
    /**
     * A function that is run once during the robot's initialization.
     */
    open val pre: ((ctx: Context) -> Unit)? = null

    /**
     * A function that is run multiple times during a robot's runtime.
     * Each component's [cycle] is run once per loop. This loop runs until the stop button is pressed.
     */
    open val cycle: ((ctx: Context) -> Unit)? = null

    /**
     * A function that is run once during the robot's shutdown phase.
     */
    open val post: ((ctx: Context) -> Unit)? = null

    /**
     * The order that a component's functions are run.
     *
     * Smaller numbers are run earlier in the phase, larger numbers are run later in the phase.
     * When multiple components have the same ordering, the [runtime builder][org.firstinspires.ftc.teamcode.api.arch.RuntimeBuilder] chooses which goes first.
     */
    open val order: Byte = 0

    /**
     * Determines what kind of opmode this component should be run on.
     */
    open val opmode: OpMode = OpMode.Any
}
