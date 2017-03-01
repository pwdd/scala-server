package server.response

import java.io.{ByteArrayInputStream, InputStream}
import java.nio.file.Path

import server.request.Request

object HelloWorldResponder extends Responder {
  val text = "Hello, world!"

  def header(uri: Path, request: Request): InputStream = {
    new ByteArrayInputStream(
      ("HTTP/1.1 200 OK" + CRLF +
      "Date: " + formattedDate + CRLF +
      "ETag: abcdefg" + CRLF +
      "Cache-Control: max-age=120" + CRLF +
      "Last-Modified: Tue, 24 Jan 2012 00:09:09 GMT" + CRLF +
      "Content-type: text/plain" + CRLF +
      "Content-Length: " + text.size + CRLF + CRLF).getBytes)
  }

  def body(uri: Path, request: Request): InputStream = new ByteArrayInputStream(text.getBytes)
}
