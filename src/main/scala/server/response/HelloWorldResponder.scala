package server.response

import java.nio.file.Path

object HelloWorldResponder extends Responder {
  def header(uri: Path): Array[Byte] = {
    ("HTTP/1.1 200 OK" + CRLF +
      "Date: " + date + CRLF +
      "Content-Length: " + size(uri) + CRLF + CRLF).getBytes
  }

  def body(uri: Path): Array[Byte] = "Hello, world!".getBytes
}
