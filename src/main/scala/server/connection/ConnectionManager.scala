package server.connection

import java.io._
import java.net.Socket

import server.request.Request
import server.routes.Settings
import server.response.Response

case class ConnectionManager(socket: Socket) extends Runnable {

  def bufferedRequest: BufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream))

  def sendResponse(response: InputStream): Unit = {
    val buf = new Array[Byte](1024)
    var bytesRead = 0
    val out = new BufferedOutputStream(socket.getOutputStream)
    while ({bytesRead = response.read(buf); bytesRead != -1}) {
      out.write(buf, 0, bytesRead)
      out.flush()
    }
    response.close()
    out.close()
  }

  override def run(): Unit = {
    val request = Request(bufferedRequest)
    println("\n\n" + request.requestMap + "\n\n")
    val response = Response.handle(Settings.rootDirectory, request, Settings.routes)
    sendResponse(response)
  }
}

