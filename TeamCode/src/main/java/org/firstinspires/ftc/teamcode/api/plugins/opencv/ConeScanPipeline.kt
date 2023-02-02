package org.firstinspires.ftc.teamcode.api.plugins.opencv

import com.acmerobotics.dashboard.config.Config
import org.opencv.core.Mat
import org.opencv.core.Rect
import org.opencv.core.Scalar
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc
import org.openftc.easyopencv.OpenCvPipeline

@Config
private object ConeScanConfig {
    /**
     * The value to multiply against a pixel's color in order to increase / decrease **contrast**.
     *
     * `NEW_IMG = IMAGE_MULTIPLIER * IMG + IMAGE_OFFSET`
     */
    @JvmField var IMAGE_MULTIPLIER: Double = 0.6

    /**
     * The value to add against a pixel's color in order increase / decrease **brightness**.
     *
     * `NEW_IMG = IMAGE_MULTIPLIER * IMG + IMAGE_OFFSET`
     */
    @JvmField var IMAGE_OFFSET: Double = 0.0

    /**
     * Size of blur kernel, A.K.A. the amount of surrounding pixels a single pixel is allowed to
     * sample.
     */
    @JvmField var BLUR_KERNEL_SIZE: Double = 2.0

    @JvmField var IMAGE_X: Int = 0
    @JvmField var IMAGE_Y: Int = 0
    @JvmField var IMAGE_WIDTH: Int = 240
    @JvmField var IMAGE_HEIGHT: Int = 320
}

class ConeScanPipeline(private val sampler: ColorSamplers = ColorSamplers.RANDOM) : OpenCvPipeline() {
    enum class Color {
        Red,
        Green,
        Blue,
    }

    var output = Color.Green
        private set

    // Matrix phases
    private var cropped = Mat()
    private var filtered = Mat()
    private var blurred = Mat()

    override fun processFrame(input: Mat?): Mat {
        input!!.convertTo(filtered, -1, ConeScanConfig.IMAGE_MULTIPLIER, ConeScanConfig.IMAGE_OFFSET)
        Imgproc.blur(filtered, blurred, Size(ConeScanConfig.BLUR_KERNEL_SIZE, ConeScanConfig.BLUR_KERNEL_SIZE))

        cropped.release()
        val croppedDimensions = Rect(ConeScanConfig.IMAGE_X, ConeScanConfig.IMAGE_Y, ConeScanConfig.IMAGE_WIDTH, ConeScanConfig.IMAGE_HEIGHT)
        cropped = blurred.submat(croppedDimensions)

        output = sampler.sample(blurred)

        Imgproc.rectangle(blurred, croppedDimensions, Scalar(0.0, 255.0, 0.0))

        return blurred
    }
}
