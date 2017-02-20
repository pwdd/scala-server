package server.connection

import java.io.BufferedReader

import org.scalatest.FunSuite

trait ConnectionManagerSuite extends FunSuite {
  test("getRequest: gets request from a socket") {
    val connectionManager: ConnectionManager = ConnectionManager(MockSocket)
    val request = "GET / HTTP/1.1\r\nHost: localhost\r\n\r\n"
    val expected = request.replace("\r\n", "")
    MockSocket.setRequestString(request)
    val requested = bufToString(connectionManager.bufferedRequest)
    assert(expected == requested)
  }

  test("sendResponse: sends response through socket") {
    val connectionManager: ConnectionManager = ConnectionManager(MockSocket)
    connectionManager.sendResponse("foo".getBytes)
    MockSocket.setStoredOutput()
    assert("foo" ==  MockSocket.storedOutput)
  }

  private def bufToString(toRead: BufferedReader): String = {
    Stream.continually(toRead.readLine()).takeWhile(_ != null).mkString
  }
}

