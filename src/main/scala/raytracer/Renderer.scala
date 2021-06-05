package raytracer

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.color.{Color, RGBColor, X11Colorlist}
import com.sksamuel.scrimage.pixels.Pixel

object Renderer {
  type Vert = (Float, Float, Float)
  type Point = (Float, Float, Float)
  type Vector3 = (Float, Float, Float)
  type Face = (Int, Int, Int)

  def render(scene: Scene, renderSettings: RenderSettings): ImmutableImage = {
    val pixels: Array[Pixel] = 0.until(renderSettings.height).toArray.flatMap(x => {
      0.until(renderSettings.width).toArray.map(y => {
        val ray = Ray(new Point(x, y, 0), new Vector3(0, 0, 1))
        val color = trace(ray)
        color.toPixel(x, y)
      })
    })

    ImmutableImage.create(renderSettings.width, renderSettings.height, pixels)
  }

  def trace(ray: Ray): Color = {
    X11Colorlist.Black
  }
}
