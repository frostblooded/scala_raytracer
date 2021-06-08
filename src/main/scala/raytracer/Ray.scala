package raytracer

import raytracer.Renderer.Vector3

case class Ray(
  id: Int,
  start: Vector3,
  dir: Vector3,
  depth: Int
)
