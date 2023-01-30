package org.firstinspires.ftc.teamcode.api.plugins.opencv

import com.acmerobotics.dashboard.config.Config
import org.opencv.core.Mat
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

    @JvmField var BLUR_KERNEL_SIZE: Int = 2
}

class ConeScanPipeline(private val sampler: ColorSampler = defaultColorSample()) : OpenCvPipeline() {
    enum class Color {
        Red,
        Green,
        Blue,
    }

    var output = Color.Green
        private set

    // Matrix phases
    private var filtered = Mat()

    override fun processFrame(input: Mat?): Mat {
        input!!.convertTo(filtered, -1, ConeScanConfig.IMAGE_MULTIPLIER, ConeScanConfig.IMAGE_OFFSET)

        output = sampler.sample(filtered)

        return filtered
    }
}
