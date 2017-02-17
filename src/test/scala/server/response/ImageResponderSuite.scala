package server.response

import java.nio.file.{Path, Paths}

import org.scalatest.FunSuite

class ImageResponderSuite extends FunSuite {
  private val image: Path = Paths.get(System.getProperty("user.dir"), "src/test/scala/mocks/foo.png")

  test("header: holds protocol version and status code") {
    assert(ImageResponder.header(image) contains "HTTP/1.1 200 OK")
  }

  test("header: has date information") {
    assert(ImageResponder.header(image) contains "Date:")
  }

  test("header: has double CRLF before body") {
    assert(ImageResponder.header(image) contains "\r\n\r\n")
  }

  test("header: has Content-Length information") {
    assert(ImageResponder.header(image) contains "Content-Length")
  }

  test("header: has Content-type information") {
    assert(ImageResponder.header(image) contains "Content-type")
  }

  test("body: has the same content as image file") {}
}

