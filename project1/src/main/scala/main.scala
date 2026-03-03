package project1

import org.apache.spark.sql.SparkSession
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import scala.util.Try

case class TaskConfig(input: String, output: String)

object Main {

  def main(args: Array[String]): Unit = {

    if (args.length != 2) {
      throw new IllegalArgumentException(
        "Usage: <task> <json_config>"
      )
    }

    val spark = SparkSession.builder()
      .appName("Project1 Main")
      .getOrCreate()

    try {

      val taskName = args(0)
      val rawJson = args(1)

      val mapper = new ObjectMapper()
      mapper.registerModule(DefaultScalaModule)

      val cleanedJson =
        if (rawJson.trim.startsWith("\"")) {
          mapper.readValue(rawJson, classOf[String])
        } else {
          rawJson
        }

      println(s"Parsing JSON config: $cleanedJson")

      val config = Try {
        mapper.readValue(cleanedJson, classOf[TaskConfig])
      }.getOrElse {
        throw new IllegalArgumentException("Invalid JSON config")
      }

      taskName match {

        case "task1" =>
          pipeline1.Task1.run(
            spark,
            config.input,
            config.output
          )

        case _ =>
          throw new IllegalArgumentException(
            s"Unknown task: $taskName"
          )
      }

    } finally {
      spark.stop()
    }
  }
}