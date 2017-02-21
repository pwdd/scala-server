package server.routes

trait SetUp {
  type Routes = List[Handler]
  val routes: Routes
  val rootDirectory: String
}
