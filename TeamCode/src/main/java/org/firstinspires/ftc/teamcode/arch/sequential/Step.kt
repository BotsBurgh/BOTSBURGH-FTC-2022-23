package org.firstinspires.ftc.teamcode.arch.sequential

import org.firstinspires.ftc.teamcode.arch.base.CtxFunc

abstract class Step {
    /**
     * Initialization code
     */
    open val pre: CtxFunc? = null

    /**
     * Main run code
     */
    open val main: CtxFunc? = null
}
