package org.firstinspires.ftc.teamcode.arch.base

/**
 * Abstract class that all plugins inherit.
 */
abstract class Plugin {
    private var _ctx: Context? = null

    /**
     * A reference to the runtime [Context].
     *
     * @throws NullPointerException If the context is accessed before plugin initialization.
     */
    protected val ctx: Context
        get() = this._ctx!!

    /**
     * A private intrinsics function. **DO NOT USE THIS!**
     *
     * This function is related to the internals of the Arch API with [Plugin] and [Context]
     * initialization.
     *
     * @suppress
     */
    fun _init(ctx: Context) {
        this._ctx = ctx
        this.init()
    }

    /**
     * An overridable function that will be run during plugin initialization.
     *
     * While the order of actions is dependent of individual [IRuntime]s, usually plugins will be
     * registered before runnables like components.
     */
    open fun init() {}
}
