package com.infosupport.sparkjob

class SparkJobIT extends AbstractSpec {
  describe("SparkJob") {
    it("should run") {
      SparkJob.run(sparkSession, new LocalStorage(sparkSession), SparkJobConfig("test"))
    }
  }
}
