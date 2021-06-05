package raytracer

object Main {
  def main(args: Array[String]): Unit = {
    val obj = SceneObject("Object 1")((1, 1, 1), (0, 0, 0), (-1, -1, -1))((0, 1, 2))
    val scene = Scene(obj)
    val renderSettings = RenderSettings(400, 300)
    println(scene.toString())
    println(Renderer.render(scene, renderSettings))
  }
}
