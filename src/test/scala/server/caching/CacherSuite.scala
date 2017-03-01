package server.caching

import org.scalatest.FunSuite

import java.nio.file.{Files, Paths}

class CacherSuite extends FunSuite {
  test("hasBeenModifiedSince: returns false if file has same date") {
    val file = Paths.get(System.getProperty("user.dir"), "/src/test/scala/mocks/foo.txt")
    val date = Files.getLastModifiedTime(file).toMillis
    assert(!Cacher.hasBeenModifiedSince(file, date))
  }

  test("hasBeenModifiedSince: returns false if file has earlier date") {
    val file = Paths.get(System.getProperty("user.dir"), "/src/test/scala/mocks/foo.txt")
    val fileDate = Files.getLastModifiedTime(file).toMillis
    val requestedDate = fileDate + 1
    assert(!Cacher.hasBeenModifiedSince(file, requestedDate))
  }

  test("hasBeenModifiedSince: returns true if file has been modified") {
    val file = Paths.get(System.getProperty("user.dir"), "/src/test/scala/mocks/foo.txt")
    val fileDate = Files.getLastModifiedTime(file).toMillis
    val requestedDate = fileDate - 1
    assert(Cacher.hasBeenModifiedSince(file, requestedDate))
  }

  test("dateStringToMillis: parses date from 'If-Modified-Since' from request") {
    val dateString = "Tue, 24 Jan 2012 000909 GMT"
    assert(Cacher.dateStringToMillis(dateString).isInstanceOf[Long])
  }
}
