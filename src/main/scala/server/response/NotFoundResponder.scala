package server.response

import java.nio.file.Path

object NotFoundResponder extends Responder {
  def header(uri: Path): Array[Byte] = ("HTTP/1.1 400 Not Found" + CRLF + CRLF).getBytes
  def body(uri: Path): Array[Byte] = "Bummer!".getBytes
}
