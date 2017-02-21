package server.response

import java.nio.file.{Files, Path}
import java.time.format.DateTimeFormatter
import java.time.{ZoneOffset, ZonedDateTime}

trait Responder {
  val CRLF: String = "\r\n"

  def header(uri: Path): Array[Byte]
  def body(uri: Path): Array[Byte]

  def response(uri: Path): Array[Byte] = header(uri) ++ body(uri)

  def date: String = {
    val date = ZonedDateTime.now(ZoneOffset.UTC)
    val dateFormat = DateTimeFormatter.ofPattern("EE, dd MMM yyyy HH:mm:ss Z")
    dateFormat.format(date)
  }

  def size(image: Path): Int = Files.size(image).toInt
}

