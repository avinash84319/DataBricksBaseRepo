from pyspark.sql import SparkSession

def run_task(spark: SparkSession, input_path: str, output_path: str):
    print(f"Reading from {input_path}")
    input_data = spark.read.table(input_path)

    print(f"Writing to {output_path}")
    input_data.write.mode("overwrite").saveAsTable(output_path)

    print("Task completed successfully.")