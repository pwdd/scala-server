package server.request

import java.io.{BufferedReader, ByteArrayInputStream, InputStreamReader}

import org.scalatest.FunSuite

class RequestSuite extends FunSuite {
  private val CRLF = "\r\n"

  val requestString: String =
    "GET / HTTP/1.1" + CRLF  +
      "Host: localhost" + CRLF  +
      "Accept: text/html" + CRLF  +
      "Keep-Alive: 300" + CRLF  +
      "If-Modified-Since: Tue, 24 Jan 2012 000909 GMT" + CRLF +
      "ConnectionHandler: keep-alive" + CRLF  + CRLF

  val bufferedRequest = new BufferedReader(
    new InputStreamReader(
      new ByteArrayInputStream(requestString.getBytes)))

  val request = Request(bufferedRequest)

  test("requestMap: is a map with request data") {
    val expected = Map(
      "Method" -> "GET",
      "URI" -> "/",
      "Protocol" -> "HTTP/1.1",
      "Host" -> "localhost",
      "Accept" -> "text/html",
      "Keep-Alive" -> "300",
      "If-Modified-Since" -> "Tue, 24 Jan 2012 000909 GMT",
      "ConnectionHandler" -> "keep-alive"
    )

    val result = request.requestMap

    assert(expected === result)
  }

  test("body: is an empty string if request does not holds a body") {
    val withoutBody = "GET / HTTP/1.1" + CRLF + "Host: localhost" + CRLF + CRLF

    val bufferedRequest = new BufferedReader(
      new InputStreamReader(
        new ByteArrayInputStream(withoutBody.getBytes)))

    val requestWithoutBody = Request(bufferedRequest)

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
