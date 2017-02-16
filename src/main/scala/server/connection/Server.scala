package server.connection

import java.net.ServerSocket

object Server extends Runnable {
  private val portNumber = 8080
  private var serverSocket: ServerSocket = _

  def listenAt(portNumber: Int): Unit = {
    serverSocket = new ServerSocket(portNumber)
  }

  def stop(): Unit = serverSocket.close()

  override def run(): Unit = {
    listenAt(portNumber)
    while (true) {
      ConnectionManager(serverSocket.accept()).run()
    }
  }
}

