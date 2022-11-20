package org.firstinspires.ftc.teamcode.api.arch

class RuntimeBuilder {
    private var preComponents: MutableList<ComponentFunctionEx> = ArrayList()
    private var cycleComponents: MutableList<ComponentFunctionEx> = ArrayList()
    private var postComponents: MutableList<ComponentFunctionEx> = ArrayList()

    fun register(component: Component) {
        // Split component into 3 separate functions
        if (component.pre != null) {
            this.preComponents.add(
                ComponentFunctionEx(
                component.pre!!,
                order = component.order,
                opmode = component.opmode,
            )
            )
        }

        if (component.cycle != null) {
            this.cycleComponents.add(ComponentFunctionEx(
                component.cycle!!,
                order = component.order,
                opmode = component.opmode,
            ))
        }

        if (component.post != null) {
            this.postComponents.add(ComponentFunctionEx(
                component.cycle!!,
                order = component.order,
                opmode = component.opmode,
            ))
        }
    }

    fun registerPre(func: ComponentFunction, order: Byte = DEFAULT_ORDER, opmode: OpMode = DEFAULT_OPMODE) {
        this.preComponents.add(ComponentFunctionEx(
            func,
            order,
            opmode,
        ))
    }

    fun registerCycle(func: ComponentFunction, order: Byte = DEFAULT_ORDER, opmode: OpMode = DEFAULT_OPMODE) {
        this.cycleComponents.add(ComponentFunctionEx(
            func,
            order,
            opmode,
        ))
    }

    fun registerPost(func: ComponentFunction, order: Byte = DEFAULT_ORDER, opmode: OpMode = DEFAULT_OPMODE) {
        this.postComponents.add(ComponentFunctionEx(
            func,
            order,
            opmode,
        ))
    }

    fun build(cfg: Config): Runtime {
        val runtime = Runtime()

        // Sort components so lesser orders are first
        this.preComponents.sortedBy { it.order }
        this.cycleComponents.sortedBy { it.order }
        this.postComponents.sortedBy { it.order }

        // Remove components that shouldn't be run
        if (cfg.mode == RobotMode.Autonomous) {
            // Keep autonomous components
            this.preComponents.retainAll { it.opmode == OpMode.Autonomous || it.opmode == OpMode.Any }
            this.cycleComponents.retainAll { it.opmode == OpMode.Autonomous || it.opmode == OpMode.Any }
            this.postComponents.retainAll { it.opmode == OpMode.Autonomous || it.opmode == OpMode.Any }
        } else {
            // Keep teleop components
            this.preComponents.retainAll { it.opmode == OpMode.TeleOp || it.opmode == OpMode.Any }
            this.cycleComponents.retainAll { it.opmode == OpMode.TeleOp || it.opmode == OpMode.Any }
            this.postComponents.retainAll { it.opmode == OpMode.TeleOp || it.opmode == OpMode.Any }
        }

        this.preComponents.iterator().forEach { runtime.registerPre(it.func) }
        this.cycleComponents.iterator().forEach { runtime.registerCycle(it.func) }
        this.postComponents.iterator().forEach { runtime.registerPost(it.func) }

        return runtime
    }
}

private data class ComponentFunctionEx(val func: ComponentFunction, val order: Byte, val opmode: OpMode)
