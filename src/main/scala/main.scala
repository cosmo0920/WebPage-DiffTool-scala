import difflib._
import scala.collection.JavaConversions._
import diffutils.App._

object Application {
  def main(args: Array[String]) {
    val downloadDir = "tmp/"
    val original = fileToLines(downloadDir ++ "test.txt")
    val revised = fileToLines(downloadDir ++ "test2.txt")

    val patch = DiffUtils.diff(original, revised)

    for (out <- patch.getDeltas)
      println(out)
  }
}
