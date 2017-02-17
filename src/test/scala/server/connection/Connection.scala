package server.connection

import org.scalatest.{BeforeAndAfterEach, FunSuite}

class Connection extends FunSuite with BeforeAndAfterEach with ServerSuite with ConnectionManagerSuite {
  private var thread: Thread = _
  private var server: Server = _

  override def beforeEach(): Unit = {
    server = Server()
    thread = new Thread(server)
    thread.start()
  }

  override def afterEach(): Unit = {
    server.stop()
    thread.join()
  }

  override def stopServer(): Unit = server.stop()
}

