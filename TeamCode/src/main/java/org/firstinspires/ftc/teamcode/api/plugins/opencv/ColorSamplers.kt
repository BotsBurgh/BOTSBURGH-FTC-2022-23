package org.firstinspires.ftc.teamcode.api.plugins.opencv

import org.opencv.core.Mat
import org.firstinspires.ftc.teamcode.api.plugins.opencv.ConeScanPipeline.Color
import org.opencv.core.Point
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
        override fun sample(input: Mat): Color {
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

        override fun sample(input: Mat): Color {
            val samples = emptyList<DoubleArray>().toMutableList()

            // Sample random X and Y coordinates in the input
            repeat(sampleIterations) {
                val x = (0 until input.rows()).random()
                val y = (0 until input.cols()).random()

                samples.add(input.get(x, y))

                Imgproc.drawMarker(input, Point(x.toDouble(), y.toDouble()), Scalar(0.0, 255.0, 0.0))
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

            // Return which color is brightest
            return if (rgb[0] >= rgb[1] && rgb[0] >= rgb[2]) {
                Color.Red
            } else if (rgb[1] >= rgb[0] && rgb[1] >= rgb[2]) {
                Color.Green
            } else {
                Color.Blue
            }
        }
    };

    abstract fun sample(input: Mat): Color
}
