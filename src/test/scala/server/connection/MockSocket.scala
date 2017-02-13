package server.connection

import java.io.{ByteArrayInputStream, InputStream, OutputStream}
import java.net.Socket
import java.nio.charset.StandardCharsets
import java.util
import scala.collection.JavaConverters._

object MockSocket extends Socket {
  private var requestString: String = _
  private var storedOutput: String = _
  private var byteList = new util.ArrayList[Byte]()

  def setRequestString(request: String): Unit = requestString = request

  def getRequestString: String = requestString

  def setStoredOutput(): Unit = byteArrayToString(byteListToByteArray(byteList))

  override def getInputStream: InputStream = new ByteArrayInputStream(requestString.getBytes)

  override def getOutputStream: OutputStream = new OutputStream {
    override def write(b: Int): Unit = byteList.add(b.toByte)
  }

  private def byteListToByteArray(lob: util.ArrayList[Byte]): Array[Byte] = {
    val byteArray: Array[Byte] = Array.ofDim[Byte](lob.size)
    var index: Int = 0
    for (b <- lob.asScala) {
      byteArray({ index += 1; index - 1 }) = b
    }
    byteArray
  }

  private def byteArrayToString(ba: Array[Byte]) = new String(ba, StandardCharsets.UTF_8)
}
