package server.request

import java.io.{BufferedReader, ByteArrayInputStream, InputStreamReader}

import org.scalatest.FunSuite

class RequestSuite extends FunSuite {
  test("requestMap") {
    info("is a map with request data")
    val requestString =
      "GET / HTTP/1.1\r\n" +
        "Host: localhost\r\n" +
        "Accept: text/html\r\n" +
        "Keep-Alive: 300\r\n" +
        "ConnectionHandler: keep-alive\r\n" +
        "\r\n" +
        "Body: foo"

    val bufferedRequest = new BufferedReader(
      new InputStreamReader(
        new ByteArrayInputStream(requestString.getBytes)))

    val request = new Request(bufferedRequest)

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

    val result = request.map

    assert(expected === result)
  }
}
