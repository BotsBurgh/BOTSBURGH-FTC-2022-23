package org.firstinspires.ftc.teamcode.api.arch

class Runtime {
    val pre = LinkedList()
    val cycle = LinkedList()
    val post = LinkedList()

    /**
     * Low-level function that registers a component.
     *
     * This function adds all component functionality to the end of the Link.
     * If you need to control position, use RuntimeBuilder.
     */
    fun register(component: Component) {
        if (component.pre != null) {
            this.pre.pushLast(Link(component.pre!!))
        }

        if (component.cycle != null) {
            this.cycle.pushLast(Link(component.cycle!!))
        }

        if (component.post != null) {
            this.post.pushLast(Link(component.post!!))
        }
    }
}

class LinkedList {
    private var firstLink: Link? = null
    private var lastLink: Link? = null

    fun pushLast(link: Link) {
        if (this.firstLink == null) {
            this.firstLink = link
        } else {
            this.lastLink!!.next = link
        }

        this.lastLink = link
    }

    fun invoke(ctx: Context) {
        this.firstLink?.invokeRecursive(ctx)
    }
}

class Link(private val func: (Context) -> Unit) {
    var next: Link? = null

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
}
