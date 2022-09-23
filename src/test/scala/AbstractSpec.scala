import org.apache.spark.sql.SparkSession
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

/**
 * Derive your tests from this abstract spec.
 * This spec assumes you're using describe/it style tests.
 * You can specify other test styles by following the instructions here:
 * https://www.scalatest.org/user_guide/selecting_a_style
 */
abstract class AbstractSpec extends AnyFunSpec with Matchers {
  lazy val sparkSession: SparkSession = SparkSession.builder
    .master("local")
    .appName("spark-job-test")
    .getOrCreate()
}
