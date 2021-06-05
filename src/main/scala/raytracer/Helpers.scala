package raytracer

object Helpers {
  implicit class Crossable[X](xs: Iterable[X]) {
    def cross[Y](ys: Iterable[Y]): Iterable[(X, Y)] = for {x <- xs; y <- ys} yield (x, y)
  }
}
