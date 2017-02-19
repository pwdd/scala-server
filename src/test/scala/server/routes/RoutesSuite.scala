package server.routes

import org.scalatest.FunSuite

import server.httpMethods.GET

class RoutesSuite extends FunSuite {
  test("get: returns the list of Handlers") {
    assert((Routes.get()).isInstanceOf[List[Handler]])
  }

  test("get: returns a list with GET handlers") {
    val handlers = Routes.get()
    val responders = handlers map { handler => handler.responder }
    assert(responders === GET.responders)
  }
}
