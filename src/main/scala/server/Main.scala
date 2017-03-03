package server

import server.connection.Server

object Main {
  def main(args: Array[String]): Unit =  {
    println("\nRunning v0.2\n")
    Server().run()
  }
}
