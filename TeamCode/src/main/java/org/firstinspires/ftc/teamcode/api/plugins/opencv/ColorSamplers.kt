package org.firstinspires.ftc.teamcode.api.plugins.opencv

import com.acmerobotics.dashboard.FtcDashboard
import org.opencv.core.Mat
import org.firstinspires.ftc.teamcode.api.plugins.opencv.ConeScanPipeline.Color
import org.firstinspires.ftc.teamcode.arch.base.Context
import org.opencv.core.Point
import org.opencv.core.Rect
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc

/**
 * An enum of color samplers.
 *
 * All samplers have the [sample] function, which returns a [Color] based on a [Mat] picture.
 */
enum class ColorSamplers {
    /**
     * A sampler that only calculates the color based on the center-most pixel.
     *
     * This is very inaccurate method for color sampling, so do not use if there's a better
     * alternative.
     */
    @Deprecated("This sampler is inaccurate.")
    SINGLE_PIXEL {
        override fun sample(input: Mat, size: Rect): Color {
            // Find the center pixel
            val pixel = input.get(input.rows() / 2, input.cols() / 2)

            return if (pixel[0] >= pixel[1] && pixel[0] >= pixel[2]) {
                Color.Red
            } else if (pixel[1] >= pixel[0] && pixel[1] >= pixel[2]) {
                Color.Green
            } else {
                Color.Blue
            }
        }
    },

    /**
     * Samples 15 random pixels in the image, and calculates the color based on the average of those
     * sampled colors.
     */
    RANDOM {
        /**
         * The amount of times to sample a random pixel.
         */
        private val sampleIterations = 10

        override fun sample(input: Mat, size: Rect): Color {
            val samples = emptyList<DoubleArray>().toMutableList()

            // Sample random X and Y coordinates in the input
            repeat(sampleIterations) {
                val x = (size.x until size.x + size.width).random()
                val y = (size.y until size.y + size.height).random()

                samples.add(input.get(x, y))

                Imgproc.drawMarker(input, Point(x.toDouble(), y.toDouble()), Scalar(255.0, 255.0, 0.0))
            }

            var rgb = arrayOf(0.0, 0.0, 0.0)

            // Sum up all of the samples
            for (color in samples) {
                rgb[0] += color[0]
                rgb[1] += color[1]
                rgb[2] += color[2]
            }

            // Find the average from the sum of the colors
            rgb = arrayOf(rgb[0] / samples.size, rgb[1] / samples.size, rgb[2] / samples.size)

            return detectColor(rgb[0].toInt(), rgb[1].toInt(), rgb[2].toInt()) ?: Color.Blue
        }
    };

    abstract fun sample(input: Mat, size: Rect = Rect()): Color
}

const val BLACK_THRESH = 0.2
const val WHITE_THRESH = 0.9

private fun detectColor(r: Int, g: Int, b: Int): Color {
    val t = FtcDashboard.getInstance().telemetry

    val hsv = FloatArray(3)
    android.graphics.Color.RGBToHSV(r, g, b, hsv)

    t.addData("h", hsv[0])
    t.addData("s", hsv[1])
    t.addData("v", hsv[2])
    t.update()

    // If saturation is too low, unable to detect color accurately
    if (hsv[1] < 0.2) {
        if (hsv[2] > WHITE_THRESH) {
            return Color.White
        } else if (hsv[2] < BLACK_THRESH) {
            return Color.Black
        }
    }

    // If value is too low, then it's black
    if (hsv[2] < BLACK_THRESH) {
        return Color.Black
    }

    if ((hsv[0] > 320) || (hsv[0] <= 20)) {
        return Color.Red
    } else if ((hsv[0] > 20) && (hsv[0] <= 46)) {
        // Orange
        return Color.Orange
    } else if ((hsv[0] > 46) && (hsv[0] <= 64)) {
        // Yellow
        return Color.Yellow
    } else if ((hsv[0] > 70) && (hsv[0] <= 100)) {
        return Color.Green
    } else if ((hsv[0] > 160) && (hsv[0] <= 248)) {
        return Color.Blue
    } else if ((hsv[0] > 248) && (hsv[0] <= 320)) {
        // Purple
        return Color.Purple
    }

    // No idea if nothing else worked
    return Color.Gray
}
