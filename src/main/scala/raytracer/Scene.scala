package raytracer

import com.sksamuel.scrimage.color.{Color, X11Colorlist}

case class Scene(objects: List[SceneObject]) {
  override def toString: String = {
    val objectsString = objects.map(_.name).mkString(", ")
    s"Scene($objectsString)"
  }

  def trace(ray: Ray): Color = {
    intersect(ray)
      .map(info => info.obj.shade(info))
      .getOrElse(X11Colorlist.Purple)
  }

  def intersect(ray: Ray): Option[HitInfo] = {
    objects.foreach(obj => {
      val hitInfo = obj.intersect(ray)

      if(hitInfo.isDefined)
        return hitInfo
    })

    None
  }
}

object Scene {
  def apply(args: SceneObject*): Scene ={
    new Scene(args.toList)
  }
}
