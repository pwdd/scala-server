package server.connection

import java.net.ServerSocket

object Server {
  private var serverSocket: ServerSocket = _

  def listenAt(portNumber: Int): Unit = {
    serverSocket = new ServerSocket(portNumber)
  }

  def stop(): Unit = serverSocket.close()
}
