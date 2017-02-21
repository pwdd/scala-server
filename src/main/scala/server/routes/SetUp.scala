package server.routes

trait SetUp {
  val routes: List[Handler]
  val rootDirectory: String
}
