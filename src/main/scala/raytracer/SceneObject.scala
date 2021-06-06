package raytracer

import breeze.linalg.{DenseVector, cross, norm, normalize}
import com.sksamuel.scrimage.color.{Color, RGBColor, X11Colorlist}
import raytracer.Renderer.{Face, Vector3}

case class SceneObject(name: String, verts: List[Vector3], faces: List[Face]) {
  def material: Color = {
    X11Colorlist.Pink
  }

  def intersect(ray: Ray): Boolean = {
    faces.exists(intersectFace(ray, _))
  }

  def intersectFace(ray: Ray, face: Face): Boolean = {
    val orig = ray.start
    val dir = ray.dir
    val v0 = verts(face._1)
    val v1 = verts(face._2)
    val v2 = verts(face._3)
    val v0v1 = v1 - v0
    val v0v2 = v2 - v0
    val N = cross(v0v1, v0v2)
    val area2 = norm(N)

    val NDotRayDirection = N dot dir
    if(Math.abs(NDotRayDirection) < Float.MinPositiveValue)
      return false

    val d = N dot v0
    val t = ((N dot orig) + d) / NDotRayDirection
    if(t < 0) return false

    val P = orig + t * dir

    val C0 = {
      val edge = v1 - v0
      val vp = P - v0
      cross(edge, vp)
    }

    if((N dot C0) < 0) return false

    val C1 = {
      val edge = v2 - v1
      val vp = P - v1
      cross(edge, vp)
    }

    if((N dot C1) < 0) return false

    val C2 = {
      val edge = v0 - v2
      val vp = P - v2
      cross(edge, vp)
    }

    if((N dot C2) < 0) return false

    true
  }
}

object SceneObject {
  def apply(name: String)(verts: (Float, Float, Float)*)(faces: Face*): SceneObject = {
    val denseVerts = verts.map(x => DenseVector[Float](x._1, x._2, x._3)).toList
    new SceneObject(name, denseVerts, faces.toList)
  }
}
