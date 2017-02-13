package server.connection

import java.net.Socket
import java.io.IOException

import org.scalatest.FunSuite

trait ServerSuite extends FunSuite {
  def stopServer(): Unit

  test("Server accepts connection when listening") {
    val hostName = "localhost"
    val portNumber = 8080

    val client: Socket = new Socket(hostName, portNumber)
    assert(client.isConnected)

    stopServer()

    assertThrows[IOException] {
      new Socket(hostName, portNumber)
    }
  }
}