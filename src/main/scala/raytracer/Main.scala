package raytracer

import breeze.linalg.DenseVector
import com.sksamuel.scrimage.nio.{JpegWriter, PngWriter}

import java.io.File

object Main {
  def main(args: Array[String]): Unit = {
    val obj = SceneObject("Object 1")((10, 10, 10), (250, 250, 10), (250, 10, 10))((0, 1, 2))
    val scene = Scene(obj)
    val renderSettings = RenderSettings(400, 300)
    println(scene.toString())

    val renderer = new Renderer(scene, renderSettings)
    renderer.render.output(JpegWriter.Default, new File("render.jpg"))
  }
}
