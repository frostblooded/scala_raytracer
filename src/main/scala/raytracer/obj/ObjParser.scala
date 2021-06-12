package raytracer.obj

import breeze.linalg.DenseVector

import scala.io.Source
import scala.collection.BufferedIterator
import raytracer.Renderer.Face

object ObjParser {
  def readObject(lines: BufferedIterator[String]): ObjObject = {
    val name = lines.next.split(" ")(1)
    var verts = List.empty
    var faces = List.empty

    for(line <- lines) {
      if(line.startsWith("o ")) {
        return new Object()
      }
    }

    val elementsEnd = lines.indexWhere(_.startsWith("o "))

    val objectLines =
      if (elementsEnd == -1)
        lines
      else
        lines.take(elementsEnd)

    val vertLines = objectLines.filter(_.startsWith("v ")).toList
    val verts = vertLines.map(l => {
      val words = l.split(" ").tail.map(_.toDouble)
      DenseVector[Double](words(0), words(1), words(2))
    })

    val facesLines = objectLines.filter(_.startsWith("f ")).toList
    val faces = facesLines.map(l => {
      val words = l.split(" ").map(_.split("/").head).tail.map(_.toInt)
      (words(0), words(1), words(2))
    })

    ObjObject(name, verts, faces)
  }

  def readFromIter(lines: BufferedIterator[String]): Obj = {
    var objects: List[ObjObject] = List.empty

    while (lines.nonEmpty) {
      val line = lines.head

      if (line.startsWith("o ")) {
        objects = readObject(lines) +: objects
      } else {
        lines.next
      }
    }

    Obj(objects)
  }

  def read(filename: String): Obj = {
    val source = Source.fromFile(filename)
    val lines = source.getLines.buffered

    val obj = readFromIter(lines)

    source.close
    obj
  }
}
