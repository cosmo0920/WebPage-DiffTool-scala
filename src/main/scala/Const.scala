package net.cosmo0920.difftool

trait GetPageUrlConst {
  import com.typesafe.config._
  val config = ConfigFactory.load()
  val pageUrl = config.getString("page.url")
  val filename = config.getString("page.filename")
}
