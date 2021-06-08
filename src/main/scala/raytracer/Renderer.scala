package raytracer

import breeze.linalg.{DenseVector, normalize}
import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.pixels.Pixel

object Renderer {
  type Vector3 = DenseVector[Double]
  type Face = (Int, Int, Int)

  def renderSettings: RenderSettings = RenderSettings(400, 300)

  def render(scene: Scene): ImmutableImage = {
    val focalLength = 10
    var nextId = 0

    val pixels: Array[Pixel] = 0.until(renderSettings.height).toArray.flatMap(y => {
      0.until(renderSettings.width).toArray.map(x => {
        val origin = DenseVector[Double](0, 0, 0)
        val dir = normalize(DenseVector[Double](x, y, focalLength))
        val ray = Ray(nextId, origin, dir, 0)
        nextId += 1

        val color = scene.trace(ray)
        color.toPixel(x, y)
      })
    })

    ImmutableImage.create(renderSettings.width, renderSettings.height, pixels)
  }
}
