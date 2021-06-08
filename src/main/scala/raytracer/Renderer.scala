package raytracer

import breeze.linalg.{DenseVector, normalize}
import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.color.{RGBColor, X11Colorlist}
import com.sksamuel.scrimage.pixels.Pixel

object Renderer {
  type Vector3 = DenseVector[Double]
  type Face = (Int, Int, Int)

  def renderSettings: RenderSettings = RenderSettings(400, 300)

  def pixelsAverage(pixels: List[Pixel]): Pixel = {
    if(pixels.isEmpty) new Pixel(-1, -1, X11Colorlist.Purple.toARGBInt)

    // Xs and Ys of all of the pixels in the list should be the same
    val x = pixels.head.x
    val y = pixels.head.y

    val colors = pixels.map(_.toColor.toRGB)

    val rs = colors.map(_.red)
    val gs = colors.map(_.green)
    val bs = colors.map(_.blue)

    val rsAverage = rs.sum / rs.size
    val gsAverage = gs.sum / gs.size
    val bsAverage = bs.sum / bs.size

    new Pixel(x, y, new RGBColor(rsAverage, gsAverage, bsAverage).toARGBInt)
  }

  def render(scene: Scene, camera: Camera): ImmutableImage = {
    val samplesCount = 5

    val pixels: Array[List[Pixel]] = 0.until(renderSettings.height).toArray.flatMap(y => {
      println(s"Col $y of ${renderSettings.height} done!")

      0.until(renderSettings.width).toArray.map(x => {
        0.until(samplesCount).map(i => {
          scene.trace(camera.spawnRay(x, y)).toPixel(x, y)
        }).toList
      })
    })

    val averagedPixels = pixels.map(pixelsAverage)
    ImmutableImage.create(renderSettings.width, renderSettings.height, averagedPixels)
  }
}
