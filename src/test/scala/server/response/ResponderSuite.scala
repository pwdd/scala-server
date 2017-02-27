package server.response

import org.scalatest.FunSuite

import java.io.{ByteArrayInputStream, InputStream}
import java.nio.file.{Path, Paths}

import server.Helpers

class ResponderSuite extends FunSuite {
  object MockResponder extends Responder {
    def header(uri: Path): InputStream = new ByteArrayInputStream("foo".getBytes)
    def body(uri: Path): InputStream = new ByteArrayInputStream("bar".getBytes)
  }

  test("response: combines header and body") {
    val path = Paths.get("/foo")
    val header = Helpers.inputStreamToString(MockResponder.header(path))
    val body = Helpers.inputStreamToString(MockResponder.body(path))
    val response = Helpers.inputStreamToString(MockResponder.response(path))

    assert(response === header + body)
  }

  test("date: had the format 'Sun, 01 Jan 1900 00:00:00 +0000'") {
    val date = MockResponder.date
    val re = "\\w{3}, \\d{2} \\w{3} \\d{4} \\d{2}:\\d{2}:\\d{2} \\+\\d{4}"
    assert(date matches re)
  }
}
