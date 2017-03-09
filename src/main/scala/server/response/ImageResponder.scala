package server.response

import java.io.{ByteArrayInputStream, FileInputStream, InputStream}
import java.nio.file.{Files, Path}
import java.util.Date
import java.text.SimpleDateFormat

import server.request.Request
import server.caching.Cacher

object ImageResponder extends Responder {

  def header(image: Path, request: Request): InputStream = getProperHeader(image, request)

  def normalHeader(image: Path, request: Request): InputStream = new ByteArrayInputStream(
    ("HTTP/1.1 200 OK" + CRLF +
    "Date: " + formattedDate + CRLF +
    "Content-Length: " + size(image) + CRLF +
    "Content-type: image/jpeg" + CRLF +
    "Cache-Control: max-age=120" + CRLF +
    "Last-Modified: " + lastModifiedFormatted(image) + CRLF +
    "Connection: " + setConnection(request) + CRLF +
    "ETag: abcdefghijklmo" + CRLF +
    CRLF).getBytes)

  def cachedHeader(image: Path) = new ByteArrayInputStream(
    ("HTTP/1.1 304 Not Modified" + CRLF +
      "Date: " + formattedDate + CRLF +
      "Cache-Control: max-age=120" + CRLF +
      "ETag: abcdefghijklmo" + CRLF +
      "Last-Modified: " + lastModifiedFormatted(image) + CRLF +
      CRLF).getBytes)

  def body(image: Path, request: Request): InputStream = {
    if (shouldBeCached(image, request)) {
      new ByteArrayInputStream("".getBytes)
    } else {
      new FileInputStream(image.toString)
    }
  }

  def size(image: Path): Int = Files.size(image).toInt

  def lastModifiedFormatted(image: Path): String = {
    if (Files.exists(image)) {
      val formatter = new SimpleDateFormat("EE, dd MMM yyyy HH:mm:ss z")
      formatter.format(fileLastModified(image))
    } else {
      "Sun, 01 Jan 1900 00:00:00 UTC"
    }
  }

  def passedTime(image: Path) = Math.abs(Date.toEpochSecond - fileLastModified(image))

  private def fileLastModified(uri: Path) = Files.getLastModifiedTime(uri).toMillis

  private def setConnection(request: Request): String = {
    if (request.connection == "keep-alive") "keep-alive" else "close"
  }

  private def shouldBeCached(image: Path, request: Request): Boolean = {
    val requestDate = Cacher.dateStringToMillis(request.ifModifiedSince)
    !Cacher.hasBeenModifiedSince(image, requestDate) && request.pragma != "no-cache"
  }

  private def getProperHeader(image: Path, request: Request): InputStream = {
    if (shouldBeCached(image, request)) {
      cachedHeader(image)
    } else {
      normalHeader(image, request)
    }
  }
}

