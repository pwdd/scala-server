package server.response

import org.scalatest.FunSuite

class ImageResponderSuite extends FunSuite {
  test("header") {
    info("holds a valid HTTP header")
    assert(ImageResponder.header contains "HTTP/1.1 200 OK")
    assert(ImageResponder.header contains "Date:")
    assert(ImageResponder.header contains "\r\n")
  }
}
