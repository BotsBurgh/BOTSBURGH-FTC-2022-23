package org.firstinspires.ftc.teamcode.api.arch

/**
 * A plugin class that handles the context and initialization.
 */
abstract class Plugin {
    // Internal context value that may be null
    private var _ctx: Context? = null

    // Context value that can be accessed by the inheritor.
    // This will panic if the plugin is not initialized in PluginInit.
    protected val ctx: Context
        get() = this._ctx!!

    fun init(ctx: Context) {
        this._ctx = ctx
    }
}
