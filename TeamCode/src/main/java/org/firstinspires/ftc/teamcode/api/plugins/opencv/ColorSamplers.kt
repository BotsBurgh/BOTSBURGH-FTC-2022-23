package org.firstinspires.ftc.teamcode.api.plugins.opencv

import org.opencv.core.Mat
import org.firstinspires.ftc.teamcode.api.plugins.opencv.ConeScanPipeline.Color

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
     *
     * @throws NotImplementedError Does not work yet.
     */
    RANDOM {
        override fun sample(input: Mat): Color {
            TODO()
        }
    };

    abstract fun sample(input: Mat): Color
}
