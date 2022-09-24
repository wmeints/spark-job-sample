package com.infosupport.sparkjob

import org.apache.spark.sql.{Dataset, Encoder, SparkSession}

class LocalStorage(private val session: SparkSession) extends Storage {
  override def load[T: Encoder](inputPath: String): Dataset[T] = {
    session.read.parquet(inputPath).as[T]
  }

  override def save[T: Encoder](df: Dataset[T], outputPath: String): Unit = {
    df.write.parquet(outputPath)
  }
}
