package pl.marpiec.neuralnetworks.drivinggame

import javafx.scene.paint.Color

import scala.collection.mutable.ListBuffer


class Player(var x: Double,
            var y: Double,
            val width: Double,
            val length: Double,
            var direction: Double,
            var speed: Double) extends VisibleObject {

  def viewModel = List(
    new DrawableRectangle(- width / 2.0, - length / 2.0, width, length, Color.BLUE, Color.GREEN, direction)
  )

}

trait VisibleObject {
  def viewModel: Iterable[Drawable]
  def x: Double
  def y: Double
}

trait Obstacle extends VisibleObject

class RectangularObstacle(var x: Double,
                          var y: Double,
                          var width: Double,
                          var height: Double) extends Obstacle {

  def viewModel = List(
    new DrawableRectangle(- width / 2.0, - height / 2.0, width, height, Color.GREEN, Color.BLUE, 0.0)
  )

}

class GameCamera(var x: Double,
                 var y: Double,
                 var width: Double,
                 var height: Double)

object GameModel {
  def empty = new GameModel(20, ListBuffer.empty, ListBuffer.empty, new GameCamera(0, 0, 20, 20))
}

class GameModel(var trackWidth: Double,
                val players: ListBuffer[Player],
                val obstacles: ListBuffer[Obstacle],
                var camera: GameCamera) {

  def addObstacle(obstacle: Obstacle): Unit = {
    obstacles += obstacle
  }

  def addPlayer(player: Player): Unit = {
    players += player
  }

}

