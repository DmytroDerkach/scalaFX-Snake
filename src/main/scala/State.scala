import scalafx.scene.paint.Color.{Green, Red}
import scalafx.scene.shape.Rectangle

import scala.util.Random

case class State(snake: List[(Double, Double)], food: (Double, Double)) {

  def newState(dir: Int): State = {
    val (x, y) = snake.head
    val (newx, newy) = dir match {
      case 1 => (x, y - 25) // up
      case 2 => (x, y + 25) // down
      case 3 => (x - 25, y) // left
      case 4 => (x + 25, y) // right
      case _ => (x, y)
    }

    val newSnake: List[(Double, Double)] =
      if (newx < 0 || newx >= 600 || newy < 0 || newy >= 600 || snake.tail.contains((newx, newy))) {
        Snake.initialSnake
      } else if (food == (newx, newy))
        food :: snake
      else (newx, newy) :: snake.init

    val newFood =
      if (food == (newx, newy))
        Snake.randomFood()
      else
        food

    State(newSnake, newFood)
  }

  def rectangles: List[Rectangle] = Figures.square(food._1, food._2, Red) :: snake.map {
    case (x, y) => Figures.square(x,y, Green)
  }


}
