package org.firstinspires.ftc.teamcode.api.arch

typealias ComponentFunction = (ctx: Context) -> Unit

const val DEFAULT_ORDER: Byte = 0
// Enums can't be constants :(
val DEFAULT_OPMODE: OpMode = OpMode.Any

/**
 * An open class that all components should extend.
 */
abstract class Component {
    /**
     * A function that is run once during the robot's initialization.
     */
    open val pre: ComponentFunction? = null

    /**
     * A function that is run multiple times during a robot's runtime.
     * Each component's [cycle] is run once per loop. This loop runs until the stop button is pressed.
     */
    open val cycle: ComponentFunction? = null

    /**
     * A function that is run once during the robot's shutdown phase.
     */
    open val post: ComponentFunction? = null

    /**
     * The order that a component's functions are run.
     *
     * Smaller numbers are run earlier in the phase, larger numbers are run later in the phase.
     * When multiple components have the same ordering, the [runtime builder][org.firstinspires.ftc.teamcode.api.arch.RuntimeBuilder] chooses which goes first.
     */
    open val order: Byte = DEFAULT_ORDER

    /**
     * Determines what kind of opmode this component should be run on.
     */
    open val opmode: OpMode = DEFAULT_OPMODE
}
