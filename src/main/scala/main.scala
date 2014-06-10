import difflib._
import scala.collection.JavaConversions._
import diffutils.App._

object getPageUrlConst {
  val pageUrl = "example.com"
}

object Application {

  def run {
    import dispatch._, Defaults._
    val req = Http(url(getPageUrlConst.pageUrl) OK as.String)
    for (c <- req)
      println(c)
  }

  def main(args: Array[String]) {
    val downloadDir = "tmp/"
    val original = fileToLines(downloadDir ++ "test.txt")
    val revised = fileToLines(downloadDir ++ "test2.txt")

    val patch = DiffUtils.diff(original, revised)

    for (out <- patch.getDeltas)
      println(out)
    run
  }
}
