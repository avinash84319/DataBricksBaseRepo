package pipeline1

import org.apache.spark.sql.SparkSession

object Task1 {
  def run(spark: SparkSession, inputPath: String, outputPath: String): Unit = {
    val inputData = spark.table(inputPath)

    inputData
      .write
      .mode("overwrite")
      .saveAsTable(outputPath)
  }
}