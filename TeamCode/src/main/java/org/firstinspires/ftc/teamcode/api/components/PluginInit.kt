package org.firstinspires.ftc.teamcode.api.components

import org.firstinspires.ftc.teamcode.api.arch.Component
import org.firstinspires.ftc.teamcode.api.plugins.linear_slides
import org.firstinspires.ftc.teamcode.api.plugins.logger
import org.firstinspires.ftc.teamcode.api.plugins.wheels

/**
 * Workaround that initializes plugins with the context.
 *
 * Due to how plugins work, they don't have access to the context until they are initialized.
 */
class PluginInit: Component() {
    // Run at the very beginning
    override val order = Byte.MIN_VALUE

    override val pre = fun(ctx: Context) {
        ctx.logger.initPlugin(ctx)
        ctx.wheels.initPlugin(ctx)
        ctx.linear_slides.initPlugin(ctx)
    }
}
