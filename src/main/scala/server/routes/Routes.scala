package server.routes

object Routes {
  def apply(handlers: Handler*): List[Handler] = List(handlers: _*)
}

