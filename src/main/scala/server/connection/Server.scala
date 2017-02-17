package server.connection

import java.net.ServerSocket

case class Server() extends Runnable {
  private val portNumber = 8080
  private var serverSocket: ServerSocket = _

  def listen(): Unit = serverSocket  = new ServerSocket(portNumber)

  def stop(): Unit = serverSocket.close()

  override def run(): Unit = {
    listen()
    while (true) {
      new Thread(ConnectionManager(serverSocket.accept())).start()
    }
  }
}

