package raytracer

import breeze.linalg.{DenseVector, normalize}

class Camera(
  imageWidth: Int,
  imageHeight: Int,
  origin: DenseVector[Double],
  horizontal: DenseVector[Double],
  vertical: DenseVector[Double],
  lowerLeftCorner: DenseVector[Double]
){
  def spawnRay(x: Int, y: Int): Ray = {
    val u = x.toDouble / (imageWidth - 1)
    val v = y.toDouble / (imageHeight - 1)
    val dir = lowerLeftCorner + u*horizontal + v*vertical - origin
    Ray(origin, dir, 0)
  }
}

object Camera {
  def apply(
    aspectRatio: Double,
    imageWidth: Int,
    viewportHeight: Double,
    focalLength: Double,
    origin: DenseVector[Double]
  ): Camera = {
    val imageHeight = (imageWidth / aspectRatio).toInt
    val viewportWidth = aspectRatio * viewportHeight
    val horizontal = DenseVector[Double](viewportWidth, 0, 0)
    val vertical = DenseVector[Double](0, viewportHeight, 0)
    val lowerLeftCorner = origin - horizontal/2.0 - vertical/2.0 - DenseVector[Double](0, 0, focalLength)

    new Camera(
      imageWidth,
      imageHeight,
      origin,
      horizontal,
      vertical,
      lowerLeftCorner
    )
  }
}
