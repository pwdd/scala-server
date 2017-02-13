package server.connection

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

case class ConnectionManager(socket: Socket) {
  def getRequest = new BufferedReader(new InputStreamReader(socket.getInputStream))
}
