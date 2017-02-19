package server.response

import java.nio.file.{Files, Path}
import java.time.format.DateTimeFormatter
import java.time.{ZoneOffset, ZonedDateTime}

trait Responder {
  val CRLF: String = "\r\n"

  def header(image: Path): String
  def body(image: Path): Array[Byte]

  def date: String = {
    val date = ZonedDateTime.now(ZoneOffset.UTC)
    val dateFormat = DateTimeFormatter.ofPattern("EE, dd MMM yyyy HH:mm:ss Z")
    dateFormat.format(date)
  }

  def size(image: Path): Int = Files.size(image).toInt
}

