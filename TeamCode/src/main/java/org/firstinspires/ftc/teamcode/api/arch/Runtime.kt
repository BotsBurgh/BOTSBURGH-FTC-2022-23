package org.firstinspires.ftc.teamcode.api.arch

class Runtime {
    var pre: Link? = null
    var cycle: Link? = null
    var post: Link? = null

    /**
     * Low-level function that registers a pre function.
     *
     * This function adds all component functionality to the end of the Link.
     * If you need to control position, use RuntimeBuilder.
     */
    fun registerPre(func: ComponentFunction) {
        if (this.pre == null) {
            this.pre = Link(func)
        } else {
            this.pre!!.pushLast(Link(func))
        }
    }

    /**
     * Low-level function that registers a cycle function.
     *
     * This function adds all component functionality to the end of the Link.
     * If you need to control position, use RuntimeBuilder.
     */
    fun registerCycle(func: ComponentFunction) {
        if (this.cycle == null) {
            this.cycle = Link(func)
        } else {
            this.cycle!!.pushLast(Link(func))
        }
    }

    /**
     * Low-level function that registers a post function.
     *
     * This function adds all component functionality to the end of the Link.
     * If you need to control position, use RuntimeBuilder.
     */
    fun registerPost(func: ComponentFunction) {
        if (this.post == null) {
            this.post = Link(func)
        } else {
            this.post!!.pushLast(Link(func))
        }
    }
}

class Link(private val func: ComponentFunction) {
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
