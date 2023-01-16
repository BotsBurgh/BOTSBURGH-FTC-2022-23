package org.firstinspires.ftc.teamcode.arch.sequential

import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.base.CtxFunc

// Copied from ComponentList
class StepList {
    private var firstLink: StepLink? = null
    private var lastLink: StepLink? = null

    fun push(func: CtxFunc) {
        val link = StepLink(func)

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

private class StepLink(private val func: CtxFunc) {
    var next: StepLink? = null

    fun invoke(ctx: Context): StepLink? {
        this.func.invoke(ctx)
        return this.next
    }
}
