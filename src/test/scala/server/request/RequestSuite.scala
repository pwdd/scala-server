package server.request

import java.io.{BufferedReader, ByteArrayInputStream, InputStreamReader}

import org.scalatest.FunSuite

class RequestSuite extends FunSuite {
  private val CRLF = "\r\n"

  val requestString =
    "GET / HTTP/1.1" + CRLF  +
      "Host: localhost" + CRLF  +
      "Accept: text/html" + CRLF  +
      "Keep-Alive: 300" + CRLF  +
      "ConnectionHandler: keep-alive" + CRLF  +
      "" + CRLF  +
      "Body: foo"

  val bufferedRequest = new BufferedReader(
    new InputStreamReader(
      new ByteArrayInputStream(requestString.getBytes)))

  val request = new Request(bufferedRequest)

  test("requestMap: is a map with request data") {
    val expected = Map(
      "Method" -> "GET",
      "URI" -> "/",
      "Protocol" -> "HTTP/1.1",
      "Host" -> "localhost",
      "Accept" -> "text/html",
      "Keep-Alive" -> "300",
      "ConnectionHandler" -> "keep-alive",
      "Body" -> "foo"
    )

    val result = request.requestMap

    assert(expected === result)
  }

  test("body: holds the body of a request if it exists") {
    assert(request.body === "foo")
  }

  test("body: is an empty string if request does not holds a body") {
    val withoutBody = "GET / HTTP/1.1" + CRLF + "Host: localhost" + CRLF + CRLF

    val bufferedRequest = new BufferedReader(
      new InputStreamReader(
        new ByteArrayInputStream(withoutBody.getBytes)))

    val requestWithoutBody = new Request(bufferedRequest)

    assert(requestWithoutBody.body === "")
  }

  test("uri: holds the value of requested uri") {
    assert(request.uri === "/")
  }

  test("method: holds the value of requested method") {
    assert(request.method === "GET")
  }

  test("protocol: holds the value of requested protocol") {
    assert(request.protocol === "HTTP/1.1")
  }
}

