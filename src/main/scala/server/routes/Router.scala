package server.routes

import server.response.Responder

trait Router {
  def get(): List[Handler]
}

