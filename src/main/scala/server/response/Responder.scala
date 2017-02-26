package server.response

import java.io.{InputStream, SequenceInputStream}
import java.nio.file.{Files, Path}
import java.time.format.DateTimeFormatter
import java.time.{ZoneOffset, ZonedDateTime}

trait Responder {
  val CRLF: String = "\r\n"

  def header(uri: Path): InputStream
  def body(uri: Path): InputStream

  def response(uri: Path): InputStream = new SequenceInputStream(header(uri), body(uri))

  def date: String = {
    val date = ZonedDateTime.now(ZoneOffset.UTC)
    val dateFormat = DateTimeFormatter.ofPattern("EE, dd MMM yyyy HH:mm:ss Z")
    dateFormat.format(date)
  }

  def size(uri: Path): Int = Files.size(uri).toInt
}

