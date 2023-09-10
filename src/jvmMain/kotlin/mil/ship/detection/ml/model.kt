package mil.ship.detection.ml
import io.ktor.utils.io.errors.*
import org.jetbrains.kotlinx.dl.api.core.metric.Metrics
import org.jetbrains.kotlinx.dl.api.inference.savedmodel.SavedModel
import org.jetbrains.kotlinx.dl.dataset.evaluate
import org.jetbrains.kotlinx.dl.dataset.predict


import java.io.File
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import javax.imageio.ImageIO



class model {
    constructor(){
            try {
                val image = File("/Users/maxwell.idler.secman/Desktop/Workspace/Ship_Detection/src/jvmMain/resources/test.jpeg")
                this.lenetOnMnistInference()
            } catch (e: Exception) {
                e.printStackTrace()
            }
    }
    private val PATH_TO_MODEL = "/Users/maxwell.idler.secman/Desktop/Workspace/Ship_Detection/src/jvmMain/resources/resources/model"

    /**
     * This examples demonstrates running [SavedModel] for prediction on [mnist] dataset.
     *
     * It uses enum-based tensor names to get access to input/output tensors in TensorFlow static graph.
     */
    fun lenetOnMnistInference() {

        SavedModel.load(PATH_TO_MODEL).use {
            println(it.graphToString())

//            val prediction = it.predict(train.getX(0), "Placeholder", "ArgMax")
//
//            println("Predicted Label is: $prediction")
//            println("Correct Label is: " + train.getY(0))
//
//            val predictions = it.predict(test, SavedModel::predict)
//            println(predictions.toString())
//
//            println("Accuracy is : ${it.evaluate(test, Metrics.ACCURACY, SavedModel::predict)}")
        }
    }
    val model = null
}

