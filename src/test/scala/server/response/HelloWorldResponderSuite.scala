package server.response

import org.scalatest.FunSuite

import java.nio.file.Paths

class HelloWorldResponderSuite extends FunSuite {
  private val uri = Paths.get("")
  private val header = new String(HelloWorldResponder.header(uri))

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
    assert(HelloWorldResponder.body(uri) === "Hello, world!".getBytes)
  }
}
