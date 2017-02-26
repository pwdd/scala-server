package server.response

import java.io.{ByteArrayInputStream, InputStream}
import java.nio.file.Path

object HelloWorldResponder extends Responder {
  val text = "Hello, world!"

  def header(uri: Path): InputStream = {
    new ByteArrayInputStream(
      ("HTTP/1.1 200 OK" + CRLF +
      "Date: " + date + CRLF +
      "Content-type: text/plain" + CRLF +
      "Content-Length: " + text.size + CRLF + CRLF).getBytes)
  }

  def body(uri: Path): InputStream = new ByteArrayInputStream(text.getBytes)
}
