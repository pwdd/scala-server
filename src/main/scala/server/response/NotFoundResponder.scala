package server.response

import java.nio.file.Path

object NotFoundResponder extends Responder {
  private val text = "Bummer!"

  def header(uri: Path): Array[Byte] = (
    "HTTP/1.1 400 Not Found" + CRLF +
    "Date: " + date + CRLF +
    "Content-type: text/plain" + CRLF +
    "Content-Length: "  + text.length + CRLF + CRLF).getBytes

  def body(uri: Path): Array[Byte] = text.getBytes
}
