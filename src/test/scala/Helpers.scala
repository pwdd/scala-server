package server

import java.io.InputStream

object Helpers {
  def inputStreamToString(in: InputStream): String = scala.io.Source.fromInputStream(in).mkString
}

