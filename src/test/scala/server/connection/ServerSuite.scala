package server.connection

import java.net.Socket
import java.io.{BufferedReader, IOException, InputStreamReader, PrintWriter}

import org.scalatest.FunSuite

trait ServerSuite extends FunSuite {
  val hostName = "localhost"
  val portNumber = 8080

  def stopServer(): Unit

  test("Server accepts connection when listening") {
    val client: Socket = new Socket(hostName, portNumber)
    assert(client.isConnected)

    stopServer()

    assertThrows[IOException] {
      new Socket(hostName, portNumber)
    }
  }

  test("Server handles requests and responses") {
    val client: Socket = new Socket(hostName, portNumber)
    val in = new BufferedReader( new InputStreamReader( client.getInputStream))
    val out = new PrintWriter(client.getOutputStream)

    out.print("GET / HTTP/1.1\r\nHost: localhost\r\n\r\n")
    out.flush()
    val peek = in.readLine()
    client.setSoTimeout(500)
    out.close()
    client.close()
    assert("HTTP/1.1 200 OK" === peek)
  }
}

