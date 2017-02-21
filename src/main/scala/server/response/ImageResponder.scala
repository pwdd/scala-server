package server.response

import java.nio.file.{Files, Path}

object ImageResponder extends Responder {

  def header(image: Path): Array[Byte] = ("HTTP/1.1 200 OK" + CRLF +
    "Date: " + date + CRLF +
    "Content-Length: " + size(image) + CRLF +
    "Content-type: image/jpeg" + CRLF +
    CRLF).getBytes

  def body(image: Path): Array[Byte] = Files.readAllBytes(image)
}

