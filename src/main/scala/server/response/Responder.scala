package server.response

import java.io.{ByteArrayInputStream, InputStream, SequenceInputStream}
import java.nio.file.{Files, Path}
import java.time.format.DateTimeFormatter
import java.time.{ZoneOffset, ZonedDateTime}

import server.request.Request

trait Responder {
  val CRLF: String = "\r\n"
  val datePattern = "EE, dd MMM yyyy HH:mm:ss Z"
  val Date = ZonedDateTime.now(ZoneOffset.UTC)

  def header(uri: Path, request: Request): InputStream
  def body(uri: Path, request: Request): InputStream

  def response(uri: Path, request: Request): InputStream = {
    new SequenceInputStream(header(uri, request), body(uri, request))
  }

  def formattedDate: String = {
    val dateFormat = DateTimeFormatter.ofPattern(datePattern)
    dateFormat.format(Date)
  }
}

