import difflib._
import scala.collection.JavaConversions._
import com.github.nscala_time.time.Imports._
import diffutils.Utility._
import net.cosmo0920.difftool._

object Application {
  def main(args: Array[String]) {
    val date = new DateTime
    val yesterday = date - 1.day
    val fdate = date.toString("yyyyMMdd")
    val ydate = yesterday.toString("yyyyMMdd")
    val downloadDir = "tmp/"
    val page = new GetPage
    val filename = page.filename

    page.writePageToFile(downloadDir ++ s"${filename}_$fdate.html", page.getPage)

    val original = Option(fileToLines(downloadDir ++ s"${filename}_$ydate.html"))
    val revised = fileToLines(downloadDir ++ s"${filename}_$fdate.html")

    original match {
      case Some(value) => {
        if (value.isEmpty) return

        val patch = DiffUtils.diff(value, revised)
        for (out <- patch.getDeltas)
          println(out)
      }
      case None => println("cannot execute diff.")
    }
  }
}
