package org.firstinspires.ftc.teamcode.arch.runloop

import org.firstinspires.ftc.teamcode.arch.base.CtxFunc

abstract class Component {
    open val pre: CtxFunc? = null
    open val cycle: CtxFunc? = null

    open val order: Byte = 0
}
