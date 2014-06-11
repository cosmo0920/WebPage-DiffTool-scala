package net.cosmo0920.difftool

import scala.io._
import java.nio.charset.CodingErrorAction
import java.io.PrintWriter

class GetPage extends GetPageUrlConst {
  def getPage: String = {
    import dispatch._, Defaults._
    implicit val codec = Codec("UTF-8")
    codec.onMalformedInput(CodingErrorAction.REPLACE)
    codec.onUnmappableCharacter(CodingErrorAction.REPLACE)
    val req = Http(host(pageUrl).secure OK as.String).apply()
    Http.shutdown
    val src = Source.fromBytes(req.getBytes(), "UTF-8").mkString
    src
  }

  def writePageToFile(filename: String, contents: String) = {
    val out = new PrintWriter(filename)
    out.println(contents)
    out.close
  }
}
