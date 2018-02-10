import org.scalatest.FunSuite
import com.holdenkarau.spark.testing.SharedSparkContext
import com.mychaint.example.WordCount

class WordCountTest extends FunSuite with SharedSparkContext {
  private def wordCount = new WordCount

  test("get word count test") {
    val result = wordCount.get()
    assert(result)
  }
}