import org.scalatest.FunSuite
import com.holdenkarau.spark.testing.{ SharedSparkContext, RDDComparisons, DataFrameSuiteBase }
import com.mychaint.example.WordCount

class WordCountTest extends FunSuite with SharedSparkContext with RDDComparisons with DataFrameSuiteBase {


  test("get word count test") {
    val wordCount = new WordCount
    val rdd = sc.parallelize(Seq("a", "b", "c"))

    assert(true)
  }

  test("get test") {
    val wordCount = new WordCount
    val rdd = sc.parallelize(Seq("a", "b", "c"))
    val sqlCtx = sqlContext
    import sqlCtx.implicits._

    assertDataFrameEquals(rdd.toDF, wordCount.process(rdd).toDF)
  }
}