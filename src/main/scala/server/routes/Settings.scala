package server.routes

import server.response.ImageResponder

object Settings extends SetUp {
  val rootDirectory: String = "/public"

  val routes: List[Handler] = Routes(
    GET("/owl.jpg", ImageResponder)
  )
}
