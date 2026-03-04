from pyspark.sql import SparkSession
import sys
import json

from project1Python.pipeline1.task1 import run_task

def main():

    print("Arguments received:", sys.argv)

    if len(sys.argv) != 3:
        raise ValueError("Usage: <task> <json_config>")

    task_name = sys.argv[1]
    config_json = json.loads(sys.argv[2])

    spark = SparkSession.builder.appName("Project1 Main").getOrCreate()

    try:
        if task_name == "task1":
            run_task(spark, config_json["input"], config_json["output"])
        else:
            raise ValueError(f"Unknown task: {task_name}")
    finally:
        spark.stop()