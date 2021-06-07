package raytracer

import breeze.linalg.DenseVector
import raytracer.Renderer.Vector3

case class HitInfo(ray: Ray, obj: SceneObject, hitPoint: DenseVector[Double])
