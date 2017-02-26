package server.response

import org.scalatest.FunSuite

import java.nio.file.Paths

import server.Helpers

class HelloWorldResponderSuite extends FunSuite {
  private val uri = Paths.get("")
  private val header = Helpers.inputStreamToString(HelloWorldResponder.header(uri))

  test("header: has status code 200 OK") {
    assert(header contains "200 OK" )
  }

  test("header: has data attribute") {
    assert(header contains "Date: ")
  }

  test("header: has content-length attribute") {
    assert(header contains "Content-Length: ")
  }

  test("body: has 'hello, world' string") {
    assert(Helpers.inputStreamToString(HelloWorldResponder.body(uri)) === "Hello, world!")
  }
}
