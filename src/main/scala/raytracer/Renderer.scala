package raytracer

import breeze.linalg.{DenseVector, normalize}
import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.pixels.Pixel

object Renderer {
  type Vector3 = DenseVector[Double]
  type Face = (Int, Int, Int)

  def renderSettings: RenderSettings = RenderSettings(400, 300)

  def render(scene: Scene, camera: Camera): ImmutableImage = {
    val pixels: Array[Pixel] = 0.until(renderSettings.height).toArray.flatMap(y => {
      0.until(renderSettings.width).toArray.map(x => {
        val color = scene.trace(camera.spawnRay(x, y))
        color.toPixel(x, y)
      })
    })

    ImmutableImage.create(renderSettings.width, renderSettings.height, pixels)
  }
}
