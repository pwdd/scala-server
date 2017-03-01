package server.response

import java.io.{ByteArrayInputStream, InputStream}
import java.nio.file.Path

import server.request.Request

object NotFoundResponder extends Responder {
  private val text = "Bummer!"

  def header(uri: Path, request: Request): InputStream = new ByteArrayInputStream(
    ("HTTP/1.1 400 Not Found" + CRLF +
    "Date: " + formattedDate + CRLF +
    "Content-type: text/plain" + CRLF +
    "Content-Length: "  + text.length + CRLF + CRLF).getBytes)

  def body(uri: Path, request: Request): InputStream = new ByteArrayInputStream(text.getBytes)
}
