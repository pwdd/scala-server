package server.connection

import java.io.BufferedReader

import org.scalatest.FunSuite

trait ConnectionManagerSuite extends FunSuite {
  test("getRequest: gets request from a socket") {
    val connectionManager: ConnectionManager = ConnectionManager(MockSocket)
    val request = "GET / HTTP/1.1\r\nHost: localhost"
    MockSocket.setRequestString(request)
    val requested = bufToString(connectionManager.bufferedRequest).trim
    assert(request == requested)
  }

  test("sendResponse: sends response through socket") {
    val connectionManager: ConnectionManager = ConnectionManager(MockSocket)
    connectionManager.sendResponse("foo".getBytes)
    MockSocket.setStoredOutput()
    assert("foo" ==  MockSocket.storedOutput)
  }

  private def bufToString(in: BufferedReader): String = {
    def validLine(string: String): Boolean = string != null && string.nonEmpty
    Stream.continually(in.readLine()).takeWhile(line => validLine(line)).mkString("\r\n")
  }
}

