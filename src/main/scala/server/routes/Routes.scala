package server.routes

import server.response.ImageResponder

object Routes extends Router {
  def get(): List[Handler] = List(Handler("/", ImageResponder))
}

