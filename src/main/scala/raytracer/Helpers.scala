package raytracer

import breeze.linalg.{DenseVector, norm}
import com.sksamuel.scrimage.color.{Color, RGBColor}

import scala.annotation.tailrec
import scala.language.implicitConversions
import scala.util.Random

object Helpers {
  def randomVector: DenseVector[Double] = {
    DenseVector[Double](randomDouble, randomDouble, randomDouble)
  }

  def randomVector(minVal: Double, maxVal: Double): DenseVector[Double] = {
    DenseVector[Double](
      randomDouble(minVal, maxVal),
      randomDouble(minVal, maxVal),
      randomDouble(minVal, maxVal)
    )
  }

  def randomDouble: Double = {
    Random.nextInt.toDouble / (Int.MaxValue + 1)
  }

  def randomDouble(min: Double, max: Double): Double = {
    min + (max - min) * randomDouble
  }

  // I know it looks odd, but this proves to be the best algorithm for
  // finding a random vector in a unit sphere.
  @tailrec
  def randomInUnitSphere: DenseVector[Double] = {
    val randVec = randomVector(0, 1)

    if(norm(randVec) <= 1)
      randVec
    else
      randomInUnitSphere
  }

  implicit def multiplyColors(col1: Color, col2: Color): Color = {
    val rgb1 = col1.toRGB
    val rgb2 = col2.toRGB
    val constant = 1f/255

    val r: Int = Math.round(rgb1.red * rgb2.red * constant).toInt
    val g: Int = Math.round(rgb1.green * rgb2.green * constant).toInt
    val b: Int = Math.round(rgb1.blue * rgb2.blue * constant).toInt

    val res = new RGBColor(r, g, b)
    println(s"Multiplying $col1 and $col2 makes $res")
    res
  }
}
