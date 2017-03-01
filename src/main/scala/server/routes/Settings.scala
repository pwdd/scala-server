package server.routes

import server.response.{ImageResponder, HelloWorldResponder}

object Settings extends SetUp {
  val rootDirectory: String = "/public"

  val routes: Routes = Routes(
    GET("^/\\w*.+(jpg|jpeg|png)$", ImageResponder),
    GET("/hello", HelloWorldResponder)
  )
}

