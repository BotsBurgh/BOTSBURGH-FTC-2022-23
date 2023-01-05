package org.firstinspires.ftc.teamcode.arch.runtime

import org.firstinspires.ftc.teamcode.arch.Context
import org.firstinspires.ftc.teamcode.arch.CtxFunc

class LinkedList {
    private var firstLink: Link? = null
    private var lastLink: Link? = null

    fun push(link: Link) {
        // If firstLink is empty, then so is the entire list
        if (this.firstLink == null) {
            this.firstLink = link
        } else {
            this.lastLink!!.next = link
        }

        this.lastLink = link
    }

    fun invokeRecursive(ctx: Context) {
        // First statement to be run is the first
        var nextLink = this.firstLink

        // Invoke nextLink, and update it with the link to be called after that
        // At the end of each invocation, check to make sure there are still links left
        // This is a `while` loop but it checks the boolean at the end rather than the beginning
        do {
            nextLink = nextLink?.invoke(ctx)
        } while (nextLink != null)
    }
}

class Link(private val func: CtxFunc) {
    var next: Link? = null

    fun invoke(ctx: Context): Link? {
        this.func.invoke(ctx)
        return this.next
    }
}
