package server.response

import java.time.format.DateTimeFormatter
import java.time.{ZoneOffset, ZonedDateTime}

object ImageResponder {
  val header: String = "HTTP/1.1 200 OK\r\nDate: " + date + "\r\n\r\n"

  val body: Array[Byte] = null

  def date: String = {
    val date = ZonedDateTime.now(ZoneOffset.UTC)
    val dateFormat = DateTimeFormatter.ofPattern("EE, dd MMM yyyy HH:mm:ss Z")
    dateFormat.format(date)
  }
}
