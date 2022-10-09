package org.firstinspires.ftc.teamcode.api.arch

class RuntimeBuilder {
    private var components: MutableList<Component> = ArrayList()

    fun register(component: Component) {
        this.components.add(component)
    }

    fun build(): Runtime {
        val runtime = Runtime()

        // Sort components so lesser orders are first
        this.components.sortedBy { it.order }
        this.components.iterator().forEach { runtime.register(it) }

        return runtime
    }
}
