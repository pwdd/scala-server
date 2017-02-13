package server.connection

import org.scalatest.{BeforeAndAfterEach, FunSuite}

class Connection extends FunSuite with BeforeAndAfterEach with ServerSuite with ConnectionManagerSuite {

  override def beforeEach(): Unit = Server.listenAt(8080)

  override def afterEach(): Unit = Server.stop()

  override def stopServer(): Unit = Server.stop()
}
