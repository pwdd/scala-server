package server

import server.connection.Server

object Main {
  def main(args: Array[String]): Unit =  {
    println("\nRunning v0.3\n")
    Server().run()
  }
}
