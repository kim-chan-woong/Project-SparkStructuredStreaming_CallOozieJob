
import org.di.Model.ConfigModel
import org.IoC.SparkController

object Main {
  def main(args: Array[String]): Unit = {

    val sparkController = new SparkController

    sparkController.spark_streaming_starter(ConfigModel.APP_NAME)


  }
}