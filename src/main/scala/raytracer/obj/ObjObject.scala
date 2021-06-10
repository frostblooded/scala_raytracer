package raytracer.obj

import raytracer.Renderer.{Face, Vector3}

case class ObjObject(
  name: String,
  verts: List[Vector3],
  faces: List[Face]
)
