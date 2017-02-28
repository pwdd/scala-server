package server.caching

import java.nio.file.{Files, Path, Paths}
import java.util.Date
import java.text.SimpleDateFormat

object Cacher {
  def hasBeenModifiedSince(file: Path, dateInMillis: Long): Boolean = {
    val lastMofidified = Files.getLastModifiedTime(file).toMillis
    lastMofidified > dateInMillis
  }
}
