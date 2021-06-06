package raytracer

case class Scene(objects: List[SceneObject]) {
  override def toString: String = {
    val objectsString = objects.map(_.name).mkString(", ")
    s"Scene($objectsString)"
  }

  def intersect(ray: Ray): Option[SceneObject] = {
    objects.find(_.intersect(ray))
  }
}

object Scene {
  def apply(args: SceneObject*): Scene ={
    new Scene(args.toList)
  }
}
