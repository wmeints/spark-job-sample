package com.infosupport.sparkjob

class SparkJobSpec extends AbstractSpec {
  describe("sample job") {
        it("should run") {
          SparkJob.run(sparkSession, SparkJobConfig("sample.json"))
        }
  }
}
