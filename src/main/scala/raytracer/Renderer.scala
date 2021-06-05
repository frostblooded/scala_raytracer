package raytracer

import raytracer.Helpers.Crossable

object Renderer {
  type Color = (Float, Float, Float)
  type Vert = (Float, Float, Float)
  type Point = (Float, Float, Float)
  type Vector3 = (Float, Float, Float)
  type Face = (Int, Int, Int)

  def render(scene: Scene, renderSettings: RenderSettings): List[List[Color]] = {
    val pixels = 1.to(renderSettings.height) cross 1.to(renderSettings.width)

    pixels.foreach({ case (x, y) => {
      println((x, y))
    }})

    println(s"Pixels count: ${pixels.size}")

    List.empty
  }
}
