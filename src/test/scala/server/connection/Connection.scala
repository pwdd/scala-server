package server.connection

import org.scalatest.{BeforeAndAfterEach, FunSuite}

class Connection extends FunSuite with BeforeAndAfterEach with ServerSuite with ConnectionManagerSuite {
  private var thread: Thread = _

  override def beforeEach(): Unit = {
    thread = new Thread(Server)
    thread.start()
  }

  override def afterEach(): Unit = {
    Server.stop()
    thread.join()
  }

  override def stopServer(): Unit = Server.stop()
}
