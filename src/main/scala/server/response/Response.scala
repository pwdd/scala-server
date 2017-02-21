package server.response

import java.nio.file.{Path, Paths}

import server.request.Request
import server.routes._

object Response {
  def handle(rootDirectory: String, request: Request, routes: List[Handler]): Array[Byte] = {
    val uri = request.uri.toLowerCase
    val handler = getHandler(uri, routes)
    getResponse(rootDirectory, uri, handler)
  }

  def getHandler(uri: String, routes: List[Handler]): Handler = {
    val handlerOption: Option[Handler] = routes.find { handler => handler.uri.toLowerCase == uri }
    handlerOption match {
      case Some(value) => value
      case None => ResourceNotFound()
    }
  }

  private def getResponse(rootDirectory: String, uri: String, handler: Handler): Array[Byte] = {
    handler.responder.response(getFilePath(rootDirectory, uri))
  }

  private def getFilePath(rootDirectory: String, uri: String): Path = {
    Paths.get(System.getProperty("user.dir"), rootDirectory, uri)
  }
}

