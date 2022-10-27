package org.firstinspires.ftc.teamcode.api.components

import org.firstinspires.ftc.teamcode.api.arch.Component
import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.plugins.logger

/**
 * Workaround that initializes plugins with the context.
 *
 * Due to how plugins work, they don't have access to the context until they are initialized.
 */
class PluginInit: Component() {
    // Run at the very beginning
    override val order = Byte.MIN_VALUE

    override val pre = fun(ctx: Context) {
        ctx.logger.init(ctx)
    }
}
