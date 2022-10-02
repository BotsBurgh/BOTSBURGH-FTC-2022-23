package org.firstinspires.ftc.teamcode.api.arch

class Runtime {
    var pre: Link? = null
    var cycle: Link? = null
    var post: Link? = null
}

class Link(private val func: (Context) -> Unit) {
    private var next: Link? = null

    fun invoke(context: Context): Link? {
        this.func.invoke(context)
        return this.next
    }

    fun invokeRecursive(context: Context) {
        this.invoke(context)
        this.next?.invokeRecursive(context)
    }

    fun invokeRecursive(context: Context, depth: Int) {
        this.invoke(context)

        if (depth > 0) {
            this.next?.invokeRecursive(context, depth - 1)
        }
    }

    fun last(): Link {
        // If there is a link after this, call "last()" on it
        // Else, return self
        return this.next?.last() ?: this
    }

    fun pushLast(link: Link) {
        this.last().next = link
    }
}
