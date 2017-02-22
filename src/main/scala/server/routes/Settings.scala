package server.routes

import server.response.{ImageResponder, HelloWorldResponder}

object Settings extends SetUp {
  val rootDirectory: String = "/public"

  val routes: Routes = Routes(
    GET("/owl.jpg", ImageResponder),
    // world.jpg image is 113MB, bigger than Github limit
    GET("/world.jpg", ImageResponder),
    GET("/hello", HelloWorldResponder)
  )
}
