package org.firstinspires.ftc.teamcode.api.arch

class RuntimeBuilder {
    private var components: MutableList<Component> = ArrayList()

    fun register(component: Component) {
        this.components.add(component)
    }

    fun build(cfg: Config): Runtime {
        val runtime = Runtime()

        // Sort components so lesser orders are first
        this.components.sortedBy { it.order }

        // Remove components that shouldn't be run
        if (cfg.autonomous) {
            // Keep autonomous components
            this.components.retainAll { it.opmode == OpMode.Autonomous || it.opmode == OpMode.Any }
        } else {
            // Keep teleop components
            this.components.retainAll { it.opmode == OpMode.TeleOp || it.opmode == OpMode.Any }
        }

        this.components.iterator().forEach { runtime.register(it) }

        return runtime
    }
}
