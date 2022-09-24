package com.infosupport.sparkjob

import scopt.OptionParser

/** Extend this parser to include command-line arguments for the Spark job. You can store the
  * options in the SparkJobConfig class.
  */
class SparkJobConfigParser extends OptionParser[SparkJobConfig]("spark-job") {
  head("scopt", "4.x")

  opt[String]('i', "input-path").required
    .action((value, arg) => arg.copy(inputPath = value))
    .text("Input path")
}
