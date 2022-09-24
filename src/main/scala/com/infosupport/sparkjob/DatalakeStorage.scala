package com.infosupport.sparkjob
import org.apache.spark.sql.{DataFrame, Dataset, Encoder, SparkSession}

class DatalakeStorage(private val session: SparkSession) extends Storage {
  override def load[T: Encoder](inputPath: String): Dataset[T] = ???

  override def save[T : Encoder](df: Dataset[T], outputPath: String): Unit = ???
}
