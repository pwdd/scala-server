package server.response

import org.scalatest.FunSuite

import java.nio.file.Paths
import java.io.{BufferedReader, ByteArrayInputStream, InputStreamReader}

import server.Helpers
import server.request.Request

class HelloWorldResponderSuite extends FunSuite {
  private val uri = Paths.get("")
  private val buffer = new BufferedReader(
    new InputStreamReader(
      new ByteArrayInputStream("GET /hello HTTP/1.1\r\n\r\n".getBytes)))
  private val request = Request(buffer)
  private val header = Helpers.inputStreamToString(HelloWorldResponder.header(uri, request))

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
    assert(Helpers.inputStreamToString(HelloWorldResponder.body(uri, request)) === "Hello, world!")
  }
}
