package com.infosupport.sparkjob

/** Defines the configuration options for the job. Things like input-locations and output-locations
  * are a great example of options to use in a job.
  * @param inputPath
  *   Path where the input file is located.
  */
case class SparkJobConfig(inputPath: String = "")
