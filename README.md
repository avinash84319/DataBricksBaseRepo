Here’s a clean, professional `README.md` you can directly place in your repository.

---

# DataBricksBaseRepo

A scalable, multi-project **Scala + Python** repository template for building, versioning, and deploying production-grade Databricks pipelines.

This repository structure solves common issues faced when moving from Databricks notebooks to production:

* Version control challenges
* Manual dependency handling
* Poor multi-job orchestration
* Hard-to-maintain notebook-based pipelines
* Difficulty managing Scala + Python together

This setup enables **structured builds, reproducibility, and scalable job execution** across multiple pipelines.

---

## 🚀 Goals of This Repository

* Easy builds for both **Scala** and **Python**
* Clean dependency management
* Support for **multiple projects** in a single repository
* Support for **multiple jobs/tasks/pipelines per project**
* Fat JAR builds for controlled runtime environments
* Python wheel builds integrated with Gradle
* Compatible with **Databricks Jobs + Databricks Connect**

---

## 🏗 Repository Structure

```
DataBricksBaseRepo/
│
├── project1/
│   ├── build.gradle
│   └── src/main/
│       ├── project1Scala/
│       │   └── main.scala
│       └── project1Python/
│           ├── main.py
│           ├── pipeline1/
│           │   └── task1.py
│           └── setup.py
│
├── project2/
│
├── utils/
│
├── settings.gradle
└── gradlew
```

### Why `utils` as a separate project?

Keeping `utils` as a standalone Gradle project allows:

* Independent JAR builds
* Reuse across repositories
* Clean modular architecture
* Avoiding code duplication

---

## 🧠 Execution Architecture

Each project contains a **single entry point (Main)** that:

1. Accepts arguments from Databricks Job
2. Determines which task to execute
3. Parses JSON configuration
4. Executes the selected pipeline

---

## 🔁 Argument Format (Databricks Jobs)

All jobs must pass arguments in this format:

```json
["task_name", "{\"input\":\"input_table\",\"output\":\"output_table\"}"]
```

### Argument Rules

* First argument → Task selector
* Second argument → JSON configuration string
* JSON must be escaped properly in Databricks UI

---

# 🟣 Scala Setup

## Entry Point

`project1/src/main/project1Scala/main.scala`

Responsible for:

* SparkSession creation
* Task selection
* JSON parsing
* Executing pipeline object

SparkSession is created only in Main (Databricks requirement).

---

## Example Task Structure

```
project1Scala/
 └── pipeline1/
     └── Task1.scala
```

Each task exposes:

```scala
def run(spark: SparkSession, input: String, output: String)
```

---

## ⚙ Build Scala Fat JAR

```bash
./gradlew shadowJar
```

Output JAR:

```
project1/build/libs/project1-all.jar
```

Upload this JAR to Databricks and configure the job:

* Main Class: `project1.Main`
* Parameters: JSON array as shown above

---

# 🐍 Python Setup

Python tasks mirror Scala structure.

```
project1Python/
 ├── main.py
 ├── pipeline1/
 │   └── task1.py
 └── setup.py
```

## Python Entry Flow

* Read `sys.argv`
* Parse JSON config
* Select task
* Execute pipeline

---

## 📦 Build Python Wheel

From root:

```bash
./gradlew buildPythonWheel
```

Wheel output:

```
project1/src/main/project1Python/dist/
```

Upload wheel to Databricks and configure job with same argument pattern as Scala.

---

# 🔧 Gradle Configuration

Key Features in `build.gradle`:

* Scala support
* Shadow plugin (fat JAR)
* Databricks Connect dependency
* Jackson for JSON parsing
* Python wheel task integration
* Java 11 toolchain

---

## 🔢 Version Compatibility

Currently configured for:

* Scala: 2.13.x
* Spark: 4.0.x
* Databricks Connect: Compatible with serverless compute
* Java: 11

Update versions as Databricks runtime evolves.

---

# 🧪 Local Development Flow

1. Write task logic in Scala or Python
2. Add case in Main file
3. Build JAR or wheel
4. Upload artifact to Databricks
5. Configure Job parameters
6. Run

---

# 🧩 Adding New Tasks

### Scala

1. Create new object under pipeline folder
2. Implement `run()` method
3. Add case in `Main.scala`

### Python

1. Create new task file
2. Implement `run_task()`
3. Add condition in `main.py`

---

# 📦 Multi-Project Support

Projects are defined in:

```
settings.gradle
```

```groovy
include('project1')
include('utils')
```

You can:

* Build projects independently
* Share utils as JAR
* Scale to many pipelines cleanly

---

# 🛠 Why Fat JAR Instead of Cluster Libraries?

Advantages:

* Full environment control via Git
* No manual cluster dependency management
* Reproducible builds
* Easier CI/CD
* Clear version tracking

---

# 🎯 Who Is This For?

* Data Engineers working with **Scala + Python**
* Teams scaling beyond notebooks
* Engineers building production Databricks pipelines
* Multi-language Spark environments

---
