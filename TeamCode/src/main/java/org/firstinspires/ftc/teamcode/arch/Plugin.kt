package org.firstinspires.ftc.teamcode.arch

abstract class Plugin {
    private var _ctx: Context? = null

    protected val ctx: Context
        get() = this._ctx!!

    fun initPlugin(ctx: Context) {
        this._ctx = ctx
    }
}
