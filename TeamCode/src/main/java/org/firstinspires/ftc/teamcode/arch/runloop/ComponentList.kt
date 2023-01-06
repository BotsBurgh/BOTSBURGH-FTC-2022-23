package org.firstinspires.ftc.teamcode.arch.runloop

import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.base.CtxFunc

class ComponentList {
    private var firstLink: ComponentLink? = null
    private var lastLink: ComponentLink? = null

    fun push(func: CtxFunc) {
        val link = ComponentLink(func)

        if (this.firstLink == null) {
            this.firstLink = link
        } else {
            this.lastLink!!.next = link
        }

        this.lastLink = link
    }

    fun invokeRecursive(ctx: Context) {
        var nextLink = this.firstLink

        do {
            nextLink = nextLink?.invoke(ctx)
        } while (nextLink != null)
    }
}

private class ComponentLink(private val func: CtxFunc) {
    var next: ComponentLink? = null

    fun invoke(ctx: Context): ComponentLink? {
        this.func.invoke(ctx)
        return this.next
    }
}
