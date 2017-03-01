package server.caching

import java.nio.file.{Files, Path, Paths}
import java.util.Date
import java.text.SimpleDateFormat

object Cacher {
  def hasBeenModifiedSince(file: Path, dateInMillis: Long): Boolean = {
    val lastModified = Files.getLastModifiedTime(file).toMillis
    lastModified > dateInMillis
  }

  def dateStringToMillis(dateString: String): Long = {
    if (dateString.isEmpty) {
      0
    } else {
      val dateFormat = "EE, dd MMM yyyy HHmmss z"
      val formatter = new SimpleDateFormat(dateFormat)
      (formatter.parse(dateString)).getTime
    }
  }
}
