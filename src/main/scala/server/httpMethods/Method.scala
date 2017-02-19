package server.httpMethods

import server.response.{Responder, ImageResponder}

trait Method {
  def responders: List[Responder]
}

object GET extends Method {
  val responders: List[Responder] = List(ImageResponder)
}

object POST extends Method {
  val responders: List[Responder] = List()
}
