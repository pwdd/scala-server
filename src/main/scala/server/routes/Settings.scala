package server.routes

import server.response.{ImageResponder, HelloWorldResponder}

object Settings extends SetUp {
  val rootDirectory: String = "/public"

  val routes: Routes = Routes(
    GET("/owl.jpg", ImageResponder),
    GET("/world.jpg", ImageResponder),
    GET("/hello", HelloWorldResponder)
  )
}
