package com.infosupport.sparkjob

import org.apache.spark.sql.SparkSession

/** Entrypoint for the Spark job. You can run this as a regular application.
  */
object SparkJob extends App {
  val session = SparkSession.builder.appName("spark-job").getOrCreate()
  val storage = new DatalakeStorage(session)

  new SparkJobConfigParser().parse(args, SparkJobConfig()) match {
    case Some(config) => SparkJob.run(session, storage, config)
    case _            => // Do nothing, error is automatically printed to the terminal.
  }

  /** This function contains the main logic for the job. From here you can use functions from other
    * objects in the application to build a pipeline of transformations.
    *
    * @param session
    *   The spark session to use for the job
    * @param config
    *   The configuration for the job
    */
  def run(session: SparkSession, storage: Storage, config: SparkJobConfig): Unit = {
    // TODO: Write your job logic here.
  }
}
