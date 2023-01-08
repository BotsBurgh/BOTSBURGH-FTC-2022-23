package org.firstinspires.ftc.teamcode.api.plugins.opencv

import org.firstinspires.ftc.teamcode.arch.base.Context
import org.firstinspires.ftc.teamcode.arch.base.Plugin
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvInternalCamera
import org.openftc.easyopencv.OpenCvPipeline

private var opencvStore: OpenCV? = null

val Context.opencv
    get() = opencvStore!!

class OpenCV(private var pipeline: OpenCvPipeline = DefaultPipeline()): Plugin() {
    private var cameraMonitorViewId: Int? = null
    private var camera: OpenCvInternalCamera? = null

    init {
        opencvStore = this
    }

    override fun init() {
        this.cameraMonitorViewId = ctx.teleop.hardwareMap.appContext.resources.getIdentifier("cameraMonitorViewId", "id", ctx.teleop.hardwareMap.appContext.packageName)
        this.camera = OpenCvCameraFactory.getInstance().createInternalCamera(
            OpenCvInternalCamera.CameraDirection.BACK,
            this.cameraMonitorViewId!!,
        )

        this.camera!!.setPipeline(this.pipeline)
    }

    fun setPipeline(pipeline: OpenCvPipeline) {
        this.pipeline = pipeline
        this.camera!!.setPipeline(pipeline)
    }

    fun begin() {
        this.camera!!.openCameraDevice()
        this.camera!!.startStreaming(320, 240, OpenCvCameraRotation.UPSIDE_DOWN)
    }

    fun end() {
        this.camera!!.stopStreaming()
        this.camera!!.closeCameraDevice()
    }
}
