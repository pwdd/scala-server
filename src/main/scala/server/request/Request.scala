package server.request

import java.io.BufferedReader

case class Request(in: BufferedReader) {

  val requestMap: Map[String, String] = createMap()

  def body: String = getValueFor("Body")

  def uri: String = getValueFor("URI")

  def method: String = getValueFor("Method").toUpperCase

  def protocol: String = getValueFor("Protocol").toUpperCase

  def eTag: String = getValueFor("If-None-Match")

  def ifModifiedSince: String = getValueFor("If-Modified-Since")

  def connection: String = getValueFor("Connection")

  def cacheControl: String = getValueFor("Cache-Control")

  def pragma: String = getValueFor("Pragma")

  private def getValueFor(key: String) = {
    val value: Option[String] = requestMap.get(key)
    value match {
      case Some(content) => content
      case None => ""
    }
  }

  def createMap(): Map[String, String] = {

    def parseFirstLine(firstLine: String): Map[String, String] = {
      val firstLineArray = firstLine.split("\\s")
      Map(
        "Method" -> firstLineArray(0),
        "URI" -> firstLineArray(1),
        "Protocol" -> firstLineArray(2)
      )
    }

    var finalMap: Map[String, String] = Map()
    val string = bufToString(in).replace(":", "")
    val array = string.split("\r\n")

    val firstLineMap = parseFirstLine(array(0))

    val rest = array.slice(1, array.length).filter(_.nonEmpty)

    for (line <- rest) {
      val splitLine = resolveSplitLine(line)
      finalMap = finalMap + (splitLine(0) -> splitLine(1))
    }

    firstLineMap ++ finalMap
  }

  private def resolveSplitLine(line: String): Array[String] = {
    val key = "If-Modified-Since"
    val baseString = "Sun, 01 Jan 1900 000000 GMT"

    if (line.startsWith(key)) {
      val start = key.length + 1
      val end = start + baseString.length
      Array[String](key, line.substring(start, end))
    } else {
      line.split(" ")
    }
  }

  private def bufToString(in: BufferedReader): String = {

    def exists(string: String) = string != null && string.nonEmpty

    Stream.continually(in.readLine()).takeWhile(str => exists(str)).mkString("\r\n")
  }
}
