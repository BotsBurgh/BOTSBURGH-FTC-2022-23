package org.firstinspires.ftc.teamcode.api.plugins

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.api.arch.Component
import org.firstinspires.ftc.teamcode.api.arch.Context
import org.firstinspires.ftc.teamcode.api.arch.OpMode
import org.firstinspires.ftc.teamcode.api.components.MotorSpin
import org.firstinspires.ftc.teamcode.api.plugins.wheels
import kotlin.math.*

// Backend that enables data persistence with the plugin.
private val motor_spin_store = MotorSpin()

// Enables Context to access logger_store.
val Context.motor_spin
    get() = motor_spin_store