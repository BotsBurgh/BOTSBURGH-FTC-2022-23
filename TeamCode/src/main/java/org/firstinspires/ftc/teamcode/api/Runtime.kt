package org.firstinspires.ftc.teamcode.api

class Runtime {
    var pre: Link? = null
    var cycle: Link? = null
    var post: Link? = null
}

class Link(private val func: () -> Unit) {
    private var next: Link? = null

    fun invoke(): Link? {
        this.func.invoke()
        return this.next
    }

    fun invokeRecursive() {
        this.invoke()
        this.next?.invokeRecursive()
    }

    fun invokeRecursive(depth: Int) {
        this.invoke()

        if (depth > 0) {
            this.next?.invokeRecursive(depth - 1)
        }
    }
}
