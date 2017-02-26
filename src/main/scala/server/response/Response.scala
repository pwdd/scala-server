package server.response

import java.io.InputStream
import java.nio.file.{Path, Paths}
import java.util.regex.{Matcher, Pattern}

import server.request.Request
import server.routes._

object Response {
  def handle(rootDirectory: String, request: Request, routes: List[Handler]): InputStream = {
    val uri = request.uri.toLowerCase
    val handler = getHandler(uri, routes)
    getResponse(rootDirectory, uri, handler)
  }

  def getHandler(uri: String, routes: List[Handler]): Handler = {
    val handlerOption: Option[Handler] = routes.find { handler => compareURI(uri, handler.uri.toLowerCase) }
    handlerOption match {
      case Some(value) => value
      case None => ResourceNotFound()
    }
  }

  private def compareURI(requestedURI: String, handlerURI: String) = {
    val pattern = Pattern.compile(handlerURI + "/?")
    pattern.matcher(requestedURI).find
  }

  private def getResponse(rootDirectory: String, uri: String, handler: Handler): InputStream = {
    handler.responder.response(getFilePath(rootDirectory, uri))
  }

  private def getFilePath(rootDirectory: String, uri: String): Path = {
    Paths.get(System.getProperty("user.dir"), rootDirectory, uri)
  }
}

