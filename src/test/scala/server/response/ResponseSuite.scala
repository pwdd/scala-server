package server.response

import org.scalatest.FunSuite

import java.io.{BufferedReader, StringReader}

import server.request.Request
import server.routes._

class ResponseSuite extends FunSuite {

  object testSettings extends SetUp {
    val routes: List[Handler] = List(GET("/foo.png", ImageResponder))
    val rootDirectory: String = "src/test/scala/mocks"
  }
  private val validRequest = Request(
    new BufferedReader(
      new StringReader("GET /foo.png HTTP/1.1\r\nHost: localhost\r\n\r\n") ))

  private val invalidRequest = Request(
    new BufferedReader(
      new StringReader("GET /bar HTTP/1.1\r\nHost: localhost\r\n\r\n") ))

  private val routes = testSettings.routes
  private val rootDirectory = testSettings.rootDirectory

  test("getHandler: returns valid handler if request is has right method and uri") {
    assert(Response.getHandler(validRequest.uri, routes) === GET("/foo.png", ImageResponder))
  }

  test("getHandler: returns ResourceNotFound handler if routes does not have a proper handler") {
    assert(Response.getHandler("/fake-resource", routes) === ResourceNotFound())
  }

  test("handle: returns response with 404 if resource is not found") {
    val result = new String(Response.handle(rootDirectory, invalidRequest, routes))
    assert(result contains "HTTP/1.1 400 Not Found\r\n\r\n")
  }

  test("handle: returns response with 200 if resource is found") {
    val result = new String(Response.handle(rootDirectory, validRequest, routes))
    assert(result contains "HTTP/1.1 200 OK")
  }
}

