# Spark job sample project

Writing Spark jobs effectively in Scala isn't very straightforward. You quickly end up with a setup where your project
is a messy spaghetti plate of Scala code that's pretty hard to test and maintain. You do test your code, right?

In this repository you'll find a skeleton setup that I like to use in my projects for writing Spark jobs.
Let's see what it looks like, so you can decide whether this is something you'd like to use.

## Getting started

* Clone this repository to disk.
* Rename the package to the name you'd like to use.
* Update the app name in `src/main/scala/SparkJob.scala` to match yours.
* Enjoy!

## How it works

### The main job function

The main object in the code is the `SparkJob.scala` file. It contains the code to bootstrap the job.
The layout looks like this:

```scala
/**
 * Entrypoint for the Spark job. You can run this as a regular application.
 */
object SparkJob extends App {
    val session = SparkSession.builder.appName("spark-job").getOrCreate()
    val storage = new DatalakeStorage(session)

    new SparkJobConfigParser().parse(args, SparkJobConfig()) match {
        case Some(config) => SparkJob.run(session, storage, config)
        case _ => // Do nothing, error is automatically printed to the terminal.
    }

    /**
     * This function contains the main logic for the job. From here you can use functions
     * from other objects in the application to build a pipeline of transformations.
     *
     * @param session The spark session to use for the job
     * @param config  The configuration for the job
     */
    def run(session: SparkSession, storage: Storage, config: SparkJobConfig): Unit = {
        //TODO: Write your job logic here.
    }
}
```

First, we'll create a new spark session with the proper app name. We leave the master setting alone here so Spark can
inject the right setting when we submit the job to the Spark cluster.

Next, we parse a set of command-line arguments and store them into the `SparkJobConfig` object. Using command-line
arguments allows you to set settings that differ between environments. For example, locally, you may want to use a
filesystem location for the dataset, while on the server you'd use a datalake location.

When we've successfully parsed the command-line arguments, we invoke the `run` function with the Spark session and the
command-line arguments stored in a `SparkJobConfig` instance.

Because we use a public `run` function that's parameterized, you can inject a different session and settings into the
function when you're running unit-tests.

### Writing transforms

One of the main things you'll write in your application is a set of transformations. I recommend using a `transforms`
scala object to store the transformation functions. Each transformation should accept an input and a configuration
object. It should return one or more outputs.

Make sure to parameterize inputs that depend on the location where the code is run. This ensures you can properly test
the code. It also makes it easier to port the code between various environments.

### Writing tests

Speaking of tests, I highly recommend writing automated tests for your Spark jobs. The sample project contains a base
class to help you on your way.

```scala
abstract class AbstractSpec extends AnyFunSpec with Matchers with BeforeAndAfterAll {
    lazy val sparkSession: SparkSession = SparkSession.builder
        .master("local")
        .appName("spark-job-test")
        .getOrCreate()

    override def afterAll(): Unit = {
        sparkSession.stop()
    }
}
```

The base spec doesn't contain a lot of logic. You can access a test spark session through the `sparkSession` lazy
variable. You can write a test as follows:

```scala
class MySparkJobSpec extends com.infosupport.sparkjob.AbstractSpec {
    describe("someTransform") {
        it("returns data") {
            val output = transform(sparkSession.read.json("test.json"))
        }
    }
}
```

I recommend writing tests for each transformation function at least. You can use files as input for the tests. But it
promotes a workflow where you'll quickly end up using production data. It makes things harder than they need to be.

Since you can create in-memory data frames using the `createDataframe` method on the Spark session, I recommend using
that instead. It's much faster, and you can easily create a test dataset that's representative of the data you'll be
using in production.

In addition to unit-tests, I recommend writing a couple of integration tests. These tests should run the full job
against a local storage source. This gives you a somewhat comparable environment to what you'll get in a full Spark
cluster.

### Working with schemas

As tempting as it may sound, working with untyped data frames is a bad idea. You'll quickly end up with a lot of
hard-to-debug errors. It's much better to work with typed data frames. This requires you to define a schema for the
data you're working with.

You can use Scala case classes to define the schema. The sample project contains a `Schema.scala` file that contains
a couple of case classes. You can use these as a starting point for your own schemas.

## Important build tasks

The project contains a couple of important build tasks. You can run these tasks using the `sbt` command.
I recommend using the `sbt` shell to run these tasks. It's much faster than running the tasks from the command-line.

### Building the project

You can build the project using the `sbt assembly` command. This will create a fat jar that you can submit to the Spark.
If you're working from the `sbt` shell, you can just type `assembly`.

### Running tests

The project includes both integration tests and unit tests. You can run the using the following commands:

* `sbt test` - Runs the unit tests
* `sbt IntegrationTest/test` - Runs the integration tests

If you're running from the `sbt` shell, you can just type `test` or `IntegrationTest/test`.
