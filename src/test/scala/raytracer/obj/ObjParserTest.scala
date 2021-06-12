package raytracer.obj

import breeze.linalg.DenseVector

class ObjParserTest extends org.scalatest.FunSuite {
  test("can read basic obj file") {
    val lines = List(
      "o Test",
      "v 1.0 1.0 1.0"
    )

    val obj = ObjParser.readFromIter(lines.iterator.buffered)
    assert(obj.objects.size == 1)

    val objObject = obj.objects.head
    assert(objObject.name == "Test")
    assert(objObject.verts == List(DenseVector[Double](1, 1, 1)))
  }
}
