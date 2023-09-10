package mil.ship.detection.ml
import io.ktor.utils.io.errors.*
import org.jetbrains.kotlinx.dl.api.inference.savedmodel.SavedModel
import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import org.jetbrains.kotlinx.dl.api.core.FloatData
import org.jetbrains.kotlinx.dl.api.core.shape.TensorShape
import org.tensorflow.Tensor


import java.io.File
import java.io.FileInputStream
import java.nio.FloatBuffer
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import javax.imageio.ImageIO



class model {
    constructor(){
            try {
                val image = "C:\\Users\\idler\\Desktop\\Workspace\\ships\\Demo\\src\\jvmMain\\resources\\test.jpeg"
                this.lenetOnMnistInference(convertToFloatArray(image))
            } catch (e: Exception) {
                e.printStackTrace()
            }
    }
    private val PATH_TO_MODEL = "C:\\Users\\idler\\Desktop\\Workspace\\ships\\Demo\\src\\jvmMain\\resources\\model"

    fun lenetOnMnistInference(image: FloatData) {

        SavedModel.load(PATH_TO_MODEL).use {
            println(it.graphToString())

            val predictions = it.predict(image, "Placeholder", "ArgMax")

            println(predictions.toString())
//
//            println("Accuracy is : ${it.evaluate(test, Metrics.ACCURACY, SavedModel::predict)}")
        }
    }
    fun convertToFloatArray(filePath1: String): Pair<FloatArray, TensorShape> {
        // Load the image using Imgcodecs
        val img1 = Imgcodecs.imread(filePath1)

        // Ensure that the image is in the correct format (e.g., RGB)
        if (img1.channels() == 1) {
            Imgproc.cvtColor(img1, img1, Imgproc.COLOR_GRAY2RGB)
        }

        // Define the dimensions of the image
        val height = img1.rows()
        val width = img1.cols()
        val numChannels = 3

        // Create a 1D FloatArray to store the image data
        val floatArray = FloatArray(height * width * numChannels)

        // Convert image data to the 1D FloatArray
        var index = 0
        for (row in 0 until height) {
            for (col in 0 until width) {
                val pixel = img1.get(row, col)
                for (channel in 0 until numChannels) {
                    val value = pixel[channel]
                    floatArray[index++] = value.toFloat()
                }
            }
        }

        // Define the TensorShape based on image dimensions
        val tensorShape = TensorShape(1, height.toLong(), width.toLong(), numChannels.toLong())

        return Pair(floatArray, tensorShape)
    }

    val model = null
}

