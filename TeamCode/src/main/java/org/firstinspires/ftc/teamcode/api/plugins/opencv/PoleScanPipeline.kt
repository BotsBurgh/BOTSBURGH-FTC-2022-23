package org.firstinspires.ftc.teamcode.api.plugins.opencv

import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc
import org.openftc.easyopencv.OpenCvPipeline

class PoleScanPipeline: OpenCvPipeline() {
    var output: Double = 0.0
        private set

    // Matrice phases
    private val hsv = Mat()
    private val thresh = Mat()

    override fun processFrame(input: Mat?): Mat {
        // Convert to HSV
        Imgproc.cvtColor(input!!, hsv, Imgproc.COLOR_BGR2HSV)

        // Apply threshold
        Core.inRange(hsv, Scalar(50.0, 255.0 / 2.0, 255.0 / 2.0), Scalar(60.0, 255.0, 255.0), thresh)

        // TODO: Find largest yellow rectangle

        // TODO: Update output with center of rectangle x-value

        return thresh
    }
}
