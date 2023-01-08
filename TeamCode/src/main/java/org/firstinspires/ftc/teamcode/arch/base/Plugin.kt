package org.firstinspires.ftc.teamcode.arch.base

/**
 * Abstract class that all plugins inherit.
 */
abstract class Plugin {
    private var _ctx: Context? = null

    protected val ctx: Context
        get() = this._ctx!!

    fun _init(ctx: Context) {
        this._ctx = ctx
        this.init()
    }

    open fun init() {}
}
