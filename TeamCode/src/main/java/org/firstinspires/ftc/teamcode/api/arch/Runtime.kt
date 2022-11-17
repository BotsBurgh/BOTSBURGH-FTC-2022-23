package org.firstinspires.ftc.teamcode.api.arch

class Runtime {
    var pre: Link? = null
    var cycle: Link? = null
    var post: Link? = null

    /**
     * Low-level function that registers a component.
     *
     * This function adds all component functionality to the end of the Link.
     * If you need to control position, use RuntimeBuilder.
     */
    fun register(component: Component) {
        if (component.pre != null) {
            if (this.pre == null) {
                // Create pre
                this.pre = Link(component.pre!!)
            } else {
                // Add to pre
                this.pre!!.pushLast(Link(component.pre!!))
            }
        }

        if (component.cycle != null) {
            if (this.cycle == null) {
                this.cycle = Link(component.cycle!!)
            } else {
                this.cycle!!.pushLast(Link(component.cycle!!))
            }
        }

        if (component.post != null) {
            if (this.post == null) {
                this.post = Link(component.post!!)
            } else {
                this.post!!.pushLast(Link(component.post!!))
            }
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
