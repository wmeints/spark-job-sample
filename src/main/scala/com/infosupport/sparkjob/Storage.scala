package com.infosupport.sparkjob

import org.apache.spark.sql.{DataFrame, Dataset, Encoder}

/**
 * This trait defines an abstraction for the storage layer of the application.
 * As an example you can find a load and save method. Feel free to add more load methods and save methods.
 * For example, it can be useful to have a load<Something> method for a specific type of dataset.
 * You'll also want more specific save methods for specific types of datasets.
 */
trait Storage {
  def load[T : Encoder](inputPath: String): Dataset[T]
  def save[T : Encoder](df: Dataset[T], outputPath: String)
}
