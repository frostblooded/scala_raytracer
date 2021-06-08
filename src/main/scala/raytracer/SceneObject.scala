package raytracer

import breeze.linalg.{DenseVector, cross, norm, normalize}
import com.sksamuel.scrimage.color.{Color, X11Colorlist}
import raytracer.Renderer.{Face, Vector3}

case class SceneObject(
  name: String,
  verts: List[Vector3],
  faces: List[Face],
  color: Color
) {
  def material: Color = {
    color
  }

  def shade(scene: Scene, hitInfo: HitInfo): Color = {
    val maxDepth = 4

    if(hitInfo.ray.depth >= maxDepth) return material

    val newRayId = hitInfo.ray.id
    val newRayOrigin = hitInfo.point
    val newRayDir = normalize(Helpers.randomInUnitSphere + hitInfo.normal)
    val newRayDepth = hitInfo.ray.depth + 1
    val newRay = Ray(newRayId, newRayOrigin, newRayDir, newRayDepth)

    Helpers.multiplyColors(material, scene.trace(newRay))
  }

  def intersect(ray: Ray): Option[HitInfo] = {
    faces.foreach(f => {
      val hitInfo = intersectFace(ray, f)

      if(hitInfo.isDefined)
        return hitInfo
    })

    None
  }

  // Algorithm comes from https://www.scratchapixel.com/lessons/3d-basic-rendering/ray-tracing-rendering-a-triangle/ray-triangle-intersection-geometric-solution#:~:text=The%20ray%20can%20intersect%20the,these%20two%20vectors%20is%200)
  def intersectFace(ray: Ray, face: Face): Option[HitInfo] = {
    val orig = ray.start
    val dir = ray.dir
    val v0 = verts(face._1)
    val v1 = verts(face._2)
    val v2 = verts(face._3)
    val v0v1 = v1 - v0
    val v0v2 = v2 - v0
    val N = cross(v0v1, v0v2)

    val NDotRayDirection = N dot dir
    if(Math.abs(NDotRayDirection) < Float.MinPositiveValue)
      return None

    val d = N dot v0
    val t = ((N dot orig) + d) / NDotRayDirection
    if(t < 0) return None

    val P = orig + t * dir

    val C0 = {
      val edge = v1 - v0
      val vp = P - v0
      cross(edge, vp)
    }

    if((N dot C0) < 0) return None

    val C1 = {
      val edge = v2 - v1
      val vp = P - v1
      cross(edge, vp)
    }

    if((N dot C1) < 0) return None

    val C2 = {
      val edge = v0 - v2
      val vp = P - v2
      cross(edge, vp)
    }

    if((N dot C2) < 0) return None

    val area = norm(N) / 2
    val u = (norm(C0) / 2) / area
    val v = (norm(C1) / 2) / area
    val w = 1 - u - v
    val hitPoint = u * v0 + v * v1 + w * v2

    Some(HitInfo(ray, this, hitPoint, N))
  }
}

object SceneObject {
  def apply(name: String, color: Color)(verts: (Double, Double, Double)*)(faces: Face*): SceneObject = {
    val denseVerts = verts.map(x => DenseVector[Double](x._1, x._2, x._3)).toList
    new SceneObject(name, denseVerts, faces.toList, color)
  }
}
