package raytracer

import breeze.linalg.DenseVector
import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.color.{Color, RGBColor, X11Colorlist}
import com.sksamuel.scrimage.pixels.Pixel
import raytracer.Renderer.Vector3

class Renderer(scene: Scene, renderSettings: RenderSettings) {
  def render: ImmutableImage = {
    val pixels: Array[Pixel] = 0.until(renderSettings.height).toArray.flatMap(y => {
      0.until(renderSettings.width).toArray.map(x => {
        val ray = Ray(DenseVector[Float](x, y, 0), DenseVector(0, 0, 1))
        val color = trace(ray)
        color.toPixel(x, y)
      })
    })

    ImmutableImage.create(renderSettings.width, renderSettings.height, pixels)
  }

  def trace(ray: Ray): Color = {
    scene.intersect(ray).map(_.material).getOrElse(X11Colorlist.Purple)
  }
}

object Renderer {
  type Vector3 = DenseVector[Float]
  type Face = (Int, Int, Int)
}
