package raytracer

case class Scene(objects: List[raytracer.SceneObject]) {
  override def toString: String = {
    val objectsString = objects.map(_.name).mkString(", ")
    s"Scene($objectsString)"
  }
}

object Scene {
  def apply(args: SceneObject*): Scene ={
    new Scene(args.toList)
  }
}
