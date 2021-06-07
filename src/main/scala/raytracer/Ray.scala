package raytracer

import raytracer.Renderer.Vector3

case class Ray(
  start: Vector3,
  dir: Vector3,
  depth: Int
)
