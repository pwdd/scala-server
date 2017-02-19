package server.request

import java.io.BufferedReader

class Request(in: BufferedReader) {
  lazy val requestMap: Map[String, String] = createMap()

  def body: String = getValueFor("Body")
  def uri: String = getValueFor("URI")
  def method: String = getValueFor("Method").toUpperCase
  def protocol: String = getValueFor("Protocol").toUpperCase

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

    val rest = array.slice(1, array.length).filter(! _.isEmpty)

    for (line <- rest) {
      val splitLine = line.split(" ")
      finalMap = finalMap + (splitLine(0) -> splitLine(1))
    }

    firstLineMap ++ finalMap
  }

  private def bufToString(in: BufferedReader): String = {
    Stream.continually(in.readLine()).takeWhile(_ != null).mkString("\r\n")
  }
}
