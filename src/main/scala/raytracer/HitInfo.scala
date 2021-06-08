package raytracer

import breeze.linalg.DenseVector

case class HitInfo(
  ray: Ray,
  obj: SceneObject,
  point: DenseVector[Double],
  normal: DenseVector[Double]
)
