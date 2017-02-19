package server.response

import java.nio.file.{Files, Path}
import java.time.format.DateTimeFormatter
import java.time.{ZoneOffset, ZonedDateTime}

object ImageResponder {
  private val CRLF = "\r\n"

  def header(image: Path): String = "HTTP/1.1 200 OK" + CRLF +
    "Date: " + date + CRLF +
    "Content-Length: " + size(image) + CRLF +
    "Content-type: image/jpeg" + CRLF +
    CRLF + CRLF

  def body(image: Path): Array[Byte] = Files.readAllBytes(image)

  def date: String = {
    val date = ZonedDateTime.now(ZoneOffset.UTC)
    val dateFormat = DateTimeFormatter.ofPattern("EE, dd MMM yyyy HH:mm:ss Z")
    dateFormat.format(date)
  }

  private def size(image: Path): Int = Files.size(image).toInt
}
