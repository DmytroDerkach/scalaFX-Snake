import javafx.scene.input.KeyCode
import scalafx.application.{JFXApp3, Platform}
import scalafx.beans.property.{IntegerProperty, ObjectProperty}
import scalafx.scene.Scene
import scalafx.scene.paint.Color._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object SnakeFx extends JFXApp3 {


  override def start(): Unit = {
    val state = ObjectProperty(State(Snake.initialSnake, Snake.randomFood()))
    val frame = IntegerProperty(0)
    val direction = IntegerProperty(4) // 4 = right

    frame.onChange {
      state.update(state.value.newState(direction.value))
    }

    stage = new JFXApp3.PrimaryStage {
      width = 600
      height = 600
      scene = new Scene {
        fill = White
        content = state.value.rectangles

        onKeyPressed = key =>
          key.getCode match {
            case KeyCode.UP => direction.value = 1
            case KeyCode.DOWN => direction.value = 2
            case KeyCode.LEFT => direction.value = 3
            case KeyCode.RIGHT => direction.value = 4
          }


        state.onChange(Platform.runLater {
          content = state.value.rectangles
        })
      }

    }

    gameLoop(() => frame.update(frame.value + 1))
  }

  def gameLoop(update: () => Unit): Unit =
    Future {
      update()
      Thread.sleep(100)
    }.flatMap(_ => Future(gameLoop(update)))



}
