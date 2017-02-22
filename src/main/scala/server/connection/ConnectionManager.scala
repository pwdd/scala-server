package server.connection

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import server.request.Request
import server.routes.Settings
import server.response.Response

case class ConnectionManager(socket: Socket) extends Runnable {

  def bufferedRequest: BufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream))

  def sendResponse(response: Array[Byte]): Unit = {
    val out = socket.getOutputStream
    out.write(response)
    out.flush()
  }

  override def run(): Unit = {
    val request = Request(bufferedRequest)
    val response = Response.handle(Settings.rootDirectory, request, Settings.routes)
    sendResponse(response)
    socket.close()
  }
}
