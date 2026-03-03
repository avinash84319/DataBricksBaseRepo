error id: file://<WORKSPACE>/project1/src/main/scala/pipeline1/Task1.scala:
file://<WORKSPACE>/project1/src/main/scala/pipeline1/Task1.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -output_table.
	 -output_table#
	 -output_table().
	 -scala/Predef.output_table.
	 -scala/Predef.output_table#
	 -scala/Predef.output_table().
offset: 346
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

    val inputData = spark.table(inputPath)
    
    inputData 
    .write 
    .mode("overwrite") 
    .saveAsTable(output_tab@@le) 

    spark.stop()
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 