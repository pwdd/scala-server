package server.response

import java.io.{ByteArrayInputStream, FileInputStream, InputStream}
import java.nio.file.{Files, Path}

object ImageResponder extends Responder {

  def header(image: Path): InputStream = new ByteArrayInputStream(
    ("HTTP/1.1 200 OK" + CRLF +
    "Date: " + date + CRLF +
    "Content-Length: " + size(image) + CRLF +
    "Content-type: image/jpeg" + CRLF +
    CRLF).getBytes)

  def body(image: Path): InputStream = new FileInputStream(image.toString)

  def size(image: Path): Int = Files.size(image).toInt
}

