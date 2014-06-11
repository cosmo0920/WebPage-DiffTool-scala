import difflib._
import scala.collection.JavaConversions._
import scala.io._
import java.io.PrintWriter
import java.nio.charset.CodingErrorAction
import com.github.nscala_time.time.Imports._
import diffutils.Utility._

object getPageUrlConst {
  import com.typesafe.config._
  val config = ConfigFactory.load()
  val pageUrl = config.getString("page.url")
  val filename = config.getString("page.filename")
}

object Application {

  def run: String = {
    import dispatch._, Defaults._
    implicit val codec = Codec("UTF-8")
    codec.onMalformedInput(CodingErrorAction.REPLACE)
    codec.onUnmappableCharacter(CodingErrorAction.REPLACE)
    val req = Http(host(getPageUrlConst.pageUrl).secure OK as.String).apply()
    Http.shutdown
    val src = Source.fromBytes(req.getBytes(), "UTF-8").mkString
    src
  }

  def writeFile(filename: String, contents: String) = {
    val out = new PrintWriter(filename)
    out.println(contents)
    out.close
  }

  def main(args: Array[String]) {
    val date = new DateTime
    val yesterday = date - 1.day
    val fdate = date.toString("yyyyMMdd")
    val ydate = yesterday.toString("yyyyMMdd")
    val downloadDir = "tmp/"
    val filename = getPageUrlConst.filename

    writeFile(downloadDir ++ s"${filename}_$fdate.html", run)

    val original = fileToLines(downloadDir ++ s"${filename}_$ydate.html")
    val revised = fileToLines(downloadDir ++ s"${filename}_$fdate.html")

    val patch = DiffUtils.diff(original, revised)

    for (out <- patch.getDeltas)
      println(out)
  }
}
