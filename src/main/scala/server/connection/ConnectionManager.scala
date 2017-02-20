package server.connection

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import server.request.Request

case class ConnectionManager(socket: Socket) extends Runnable {
  def bufferedRequest: BufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream))

  def sendResponse(response: Array[Byte]): Unit = {
    val out = socket.getOutputStream
    out.write(response)
    out.flush()
  }

  override def run(): Unit = {
    val request = Request(bufferedRequest)
    sendResponse("HTTP/1.1 200 OK".getBytes)
    socket.close()
  }
}

