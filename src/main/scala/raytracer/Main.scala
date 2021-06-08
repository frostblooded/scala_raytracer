package raytracer

import com.sksamuel.scrimage.color.X11Colorlist
import com.sksamuel.scrimage.nio.JpegWriter

import java.io.File

object Main {
  def main(args: Array[String]): Unit = {
    val obj1 = SceneObject("Object 1", X11Colorlist.Red)(
      (80, 150, 10),
      (330, 150, 10),
      (180, 10, 50)
    )(
      (0, 1, 2)
    )

    val obj2 = SceneObject("Object 2", X11Colorlist.Blue)(
      (50, 250, 10),
      (300, 250, 10),
      (150, 60, 50)
    )(
      (0, 1, 2)
    )

    val scene = Scene(obj1, obj2)
    println(scene.toString())

    Renderer.render(scene).output(JpegWriter.Default, new File("render.jpg"))
  }
}
