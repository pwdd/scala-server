package server.connection

import java.io.{BufferedReader, InputStream, InputStreamReader}
import java.net.Socket

case class ConnectionManager(socket: Socket) {
  def getRequest: BufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream))

  def sendResponse(response: Array[Byte]): Unit = {
    val out = socket.getOutputStream
    out.write(response)
    out.flush()
  }
}
