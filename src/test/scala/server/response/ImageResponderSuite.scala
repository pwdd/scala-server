package server.response

import java.nio.file.{Path, Paths, Files}
import java.io.{File, FileInputStream}

import server.Helpers

import org.scalatest.FunSuite

class ImageResponderSuite extends FunSuite {
  private val image: Path = Paths.get(System.getProperty("user.dir"), "src/test/scala/mocks/foo.png")
  private val header: String = Helpers.inputStreamToString(ImageResponder.header(image))

  test("header: holds protocol version and status code") {
    assert(header contains "HTTP/1.1 200 OK")
  }

  test("header: has date information") {
    assert(header contains "Date:")
  }

  test("header: has double CRLF before body") {
    assert(header contains "\r\n\r\n")
  }

  test("header: has Content-Length information") {
    assert(header contains "Content-Length")
  }

  test("header: has Content-type information") {
    assert(header contains "Content-type")
  }

  test("body: has the same content as image file") {
    val pathString = "src/test/scala/mocks/test.jpg"
    val testFile = new File(pathString)
    val image = Paths.get(pathString)
    val expected = (new FileInputStream(image.toString)).read
    val result = (ImageResponder.body(image)).read
    assert(expected === result)
  }
}

