package raytracer

import com.sksamuel.scrimage.nio.{JpegWriter, PngWriter}

import java.io.File

object Main {
  def main(args: Array[String]): Unit = {
    val obj = SceneObject("Object 1")((1, 1, 1), (0, 0, 0), (-1, -1, -1))((0, 1, 2))
    val scene = Scene(obj)
    val renderSettings = RenderSettings(400, 300)
    println(scene.toString())

    val image = Renderer.render(scene, renderSettings)
    image.output(JpegWriter.Default, new File("test.jpg"))
  }
}
