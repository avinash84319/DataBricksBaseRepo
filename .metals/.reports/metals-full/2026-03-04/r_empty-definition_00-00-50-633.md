error id: file://<WORKSPACE>/project1/src/main/scala/pipeline1/Task1.scala:table.
file://<WORKSPACE>/project1/src/main/scala/pipeline1/Task1.scala
empty definition using pc, found symbol in pc: table.
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -spark/table.
	 -spark/table#
	 -spark/table().
	 -scala/Predef.spark.table.
	 -scala/Predef.spark.table#
	 -scala/Predef.spark.table().
offset: 248
uri: file://<WORKSPACE>/project1/src/main/scala/pipeline1/Task1.scala
text:
```scala
package pipeline1

import org.apache.spark.sql.SparkSession

object Task1 {
  def run(inputPath: String, outputPath: String): Unit = {
    val spark = SparkSession.builder()
      .appName("Task1")
      .getOrCreate()

    val inputData = spark.ta@@ble(inputPath)
    
    inputData 
    .write 
    .mode("overwrite") 
    .saveAsTable(output_table) 

    spark.stop()
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: table.