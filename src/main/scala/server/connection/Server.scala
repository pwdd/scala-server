package server.connection

import java.net.ServerSocket

case class Server() {
  private var serverSocket: ServerSocket = null

  def listenAt(portNumber: Int): Unit = {
    serverSocket = new ServerSocket(portNumber)
  }

  def stop(): Unit = serverSocket.close
}
