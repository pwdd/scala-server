package server.routes

import server.response.Responder

case class Handler(uri: String, responder: Responder)
