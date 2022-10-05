import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

object Figures {

  def square(xr: Double, yr: Double, color: Color): Rectangle = new Rectangle {
    x = xr
    y = yr
    width = 25
    height = 25
    fill = color
  }
}
