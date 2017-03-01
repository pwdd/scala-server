package server.response

import org.scalatest.FunSuite

import java.io.{BufferedReader, ByteArrayInputStream, InputStream, InputStreamReader}
import java.nio.file.{Path, Paths}

import server.Helpers
import server.request.Request

class ResponderSuite extends FunSuite {
  private val buffer = new BufferedReader(
    new InputStreamReader(
      new ByteArrayInputStream("GET /foo HTTP/1.1\r\n\r\n".getBytes)))

  private val request = Request(buffer)

  object MockResponder extends Responder {
    def header(uri: Path, request: Request): InputStream = new ByteArrayInputStream("foo".getBytes)
    def body(uri: Path, request: Request): InputStream = new ByteArrayInputStream("bar".getBytes)
  }

  test("response: combines header and body") {
    val path = Paths.get("/foo")
    val header = Helpers.inputStreamToString(MockResponder.header(path, request))
    val body = Helpers.inputStreamToString(MockResponder.body(path, request))
    val response = Helpers.inputStreamToString(MockResponder.response(path, request))

    assert(response === header + body)
  }

  test("date: had the format 'Sun, 01 Jan 1900 00:00:00 +0000'") {
    val date = MockResponder.formattedDate
    val re = "\\w{3}, \\d{2} \\w{3} \\d{4} \\d{2}:\\d{2}:\\d{2} \\+\\d{4}"
    assert(date matches re)
  }
}
