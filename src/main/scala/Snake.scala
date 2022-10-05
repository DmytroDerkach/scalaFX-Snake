import scala.util.Random

case object Snake{
  val initialSnake: List[(Double, Double)] = List(
    (250, 200),
    (225, 200),
    (200, 200)
  )

  def randomFood(): (Double, Double) =
    (Random.nextInt(24) * 25 , Random.nextInt(24) * 25)
}
