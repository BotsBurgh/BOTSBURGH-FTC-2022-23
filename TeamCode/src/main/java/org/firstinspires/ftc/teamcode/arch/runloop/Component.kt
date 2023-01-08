package org.firstinspires.ftc.teamcode.arch.runloop

import org.firstinspires.ftc.teamcode.arch.base.CtxFunc

/**
 * A component, used to specify code that will be run during the runloop.
 */
abstract class Component {
    /**
     * Initialization code
     */
    open val pre: CtxFunc? = null

    /**
     * Runloop code (repeated)
     */
    open val cycle: CtxFunc? = null

    /**
     * The order in which to run components. (Lower is run sooner.)
     */
    open val order: Byte = 0
}
