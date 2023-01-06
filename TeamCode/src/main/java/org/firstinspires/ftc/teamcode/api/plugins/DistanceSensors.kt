package org.firstinspires.ftc.teamcode.api.plugins

import com.qualcomm.robotcore.hardware.DistanceSensor
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit
import org.firstinspires.ftc.teamcode.arch.Context
import org.firstinspires.ftc.teamcode.arch.Plugin

private var distance_sensors_store: DistanceSensors? = null

val Context.distance_sensors
    get() = distance_sensors_store!!

class DistanceSensors: Plugin() {
    init {
        distance_sensors_store = this
    }

    var distanceLeft: DistanceSensor? = null
        private set
    var distanceRight: DistanceSensor? = null
        private set
    var distanceBack: DistanceSensor? = null
        private set

    override fun init() {
        this.distanceLeft = this.ctx.teleop.hardwareMap.get(DistanceSensor::class.java, "distanceLeft")
        this.distanceRight = this.ctx.teleop.hardwareMap.get(DistanceSensor::class.java, "distanceRight")
        this.distanceBack = this.ctx.teleop.hardwareMap.get(DistanceSensor::class.java, "distanceBack")
    }

    fun getLeft(): Double = distanceLeft!!.getDistance(DistanceUnit.INCH)
    fun getRight(): Double = distanceRight!!.getDistance(DistanceUnit.INCH)
    fun getBack(): Double = distanceBack!!.getDistance(DistanceUnit.INCH)
}
