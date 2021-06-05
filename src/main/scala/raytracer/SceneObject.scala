package raytracer

import raytracer.Renderer.{Face, Vert}

case class SceneObject(name: String, verts: List[Vert], faces: List[Face]) {
}

object SceneObject {
  def apply(name: String)(verts: Vert*)(faces: Face*): SceneObject = {
    new SceneObject(name, verts.toList, faces.toList)
  }
}
