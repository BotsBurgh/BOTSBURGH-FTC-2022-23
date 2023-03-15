package org.firstinspires.ftc.teamcode.api.plugins.opencv

import org.firstinspires.ftc.teamcode.arch.base.Context
import org.opencv.core.Mat
import org.openftc.easyopencv.OpenCvPipeline
import org.openftc.apriltag.AprilTagDetection
import org.openftc.apriltag.ApriltagDetectionJNI
import org.openftc.apriltag.AprilTagDetectorJNI

import java.util.ArrayList
class QRPipeline: OpenCvPipeline() {

    private var opencvStore: OpenCV? = null

    val Context.opencv
        get() = opencvStore!!

    init {
  //      opencvStore = this
    }

    //private var ArrayList<ctx.>
    var output = null
        private set

    private var contrasted = Mat()
    override fun processFrame(input: Mat?): Mat? {
        input!!.convertTo(contrasted, -1, )
        return contrasted
    }
}


