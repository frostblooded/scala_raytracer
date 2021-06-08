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
        println(s"Ray ${ray.id} hits ${info.obj.name} at depth ${info.ray.depth}")
        info
      })
      .map(info => info.obj.shade(this, info))
      .getOrElse(X11Colorlist.Purple)
  }

  def intersect(ray: Ray): Option[HitInfo] = {
    var closestHit: Option[HitInfo] = None
    var closestT = Double.MaxValue

    objects.foreach(obj => {
      val hitInfo = obj.intersect(ray)

      hitInfo match {
        case Some(info) =>
          if(closestT > info.t) {
            closestHit = Some(info)
            closestT = info.t
          }
        case None => ()
      }
    })

    closestHit
  }
}

object Scene {
  def apply(args: SceneObject*): Scene ={
    new Scene(args.toList)
  }
}
