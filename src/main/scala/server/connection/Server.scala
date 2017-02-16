package server.connection

import java.net.ServerSocket

object Server {
  private val portNumber = 8080
  private var serverSocket: ServerSocket = _

  def listenAt(portNumber: Int): Unit = {
    serverSocket = new ServerSocket(portNumber)
  }

  def stop(): Unit = serverSocket.close()

  def run(): Unit = {
    listenAt(portNumber)
    while (true) {
      new Thread(ConnectionManager(serverSocket.accept())).start()
    }
  }
}

