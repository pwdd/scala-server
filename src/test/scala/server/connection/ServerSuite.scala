package server.connection

import java.net.Socket
import java.io.IOException

import org.scalatest.FunSuite

class ServerSuite extends FunSuite {
  test("Server accepts connection when listening") {
    val server = Server()
    val hostName = "localhost"
    val portNumber = 8080

    server.listenAt(portNumber)

    val client: Socket = new Socket(hostName, portNumber)
    assert(client.isConnected)

    server.stop()

    assertThrows[IOException] {
      new Socket(hostName, portNumber)
    }
  }
}
