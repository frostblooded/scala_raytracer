package raytracer

import breeze.linalg.DenseVector
import com.sksamuel.scrimage.color.{RGBColor, X11Colorlist}
import com.sksamuel.scrimage.nio.JpegWriter

import java.io.File

object Main {
  def scene1: Scene = {
    val obj1 = SceneObject("Object 1", X11Colorlist.Red)(
      (0, 1, -1),
      (1, 0, -2),
      (0, 0, -3)
    )(
      (0, 1, 2)
    )

    val obj2 = SceneObject("Object 2", X11Colorlist.Blue)(
      (-0.2, 0.7, -1),
      (0.8, -0.3, -2),
      (-0.2, -0.3, -3)
    )(
      (0, 1, 2)
    )

    Scene(obj1, obj2)
  }

  def scene2: Scene = {
    val obj1 = SceneObject("Object 1", new RGBColor(255, 100, 100))(
      (80, 150, 10),
      (330, 150, 10),
      (180, 10, 10)
    )(
      (0, 1, 2)
    )

    Scene(obj1)
  }

  def main(args: Array[String]): Unit = {
    val scene = scene1

    val camera = Camera(
      16f/9,
      400,
      2,
      1,
      DenseVector[Double](0, 0, 0)
    )

    Renderer.render(scene, camera).output(JpegWriter.Default, new File("render.jpg"))
  }
}
