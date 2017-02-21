package server.connection

import java.net.ServerSocket

case class Server() extends Runnable {
  private val portNumber = 8080
  private var serverSocket: ServerSocket = _
  private var listening: Boolean = false

  def listen(): Unit = {
    serverSocket  = new ServerSocket(portNumber)
    listening = true
  }


  def stop(): Unit = {
    serverSocket.close()
    listening = false
  }

  def run(): Unit = {
    listen()
    while (listening) {
      new Thread(ConnectionManager(serverSocket.accept())).start()
    }
  }
}

