package server.routes

import server.response.{NotFound, Responder}

trait Handler {
  val uri: String
  val responder: Responder
}

case class GET(uri: String, responder: Responder) extends Handler
case class POST(uri: String, responder: Responder) extends Handler
case class ResourceNotFound(uri:String = "", responder: Responder = NotFound) extends Handler
