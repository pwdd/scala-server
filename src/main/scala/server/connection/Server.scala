package server.connection

import java.net.ServerSocket
import java.util.concurrent.{ExecutorService, Executors, TimeUnit}

case class Server() extends Runnable {
  private val portNumber = 8080
  private var serverSocket: ServerSocket = _
  private var listening: Boolean = false
  private val pool: ExecutorService = Executors.newFixedThreadPool(10)

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
      pool.execute(ConnectionManager(serverSocket.accept()))
    }
  }
}

