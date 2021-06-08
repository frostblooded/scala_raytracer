package raytracer

import com.sksamuel.scrimage.color.{Color, X11Colorlist}

case class Scene(objects: List[SceneObject]) {
  override def toString: String = {
    val objectsString = objects.map(_.name).mkString(", ")
    s"Scene($objectsString)"
  }

  def trace(ray: Ray): Color = {
    intersect(ray)
      .map(info => {
        // println(s"Hit ${info.obj.name} at depth ${info.ray.depth}")
        info
      })
      .map(info => info.obj.shade(this, info))
      .getOrElse(X11Colorlist.Purple)
  }

  def intersect(ray: Ray): Option[HitInfo] = {
    var closestHit: Option[HitInfo] = None

    objects.foreach(obj =>
      obj.intersect(ray, closestHit) match {
        case Some(hit) =>
          closestHit match {
            case Some(closest) =>
              if (closest.t > hit.t) closestHit = Some(hit)
            case None =>
              closestHit = Some(hit)
          }
        case None => ()
      }
    )

    closestHit
  }
}

object Scene {
  def apply(args: SceneObject*): Scene ={
    new Scene(args.toList)
  }
}
