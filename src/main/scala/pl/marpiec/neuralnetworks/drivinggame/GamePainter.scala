package pl.marpiec.neuralnetworks.drivinggame

import javafx.scene.transform.{Affine, Rotate}

class GamePainter(val gameCanvas: GameCanvas, val gameModel: GameModel) {

  def paint(): Unit = {

    gameCanvas.clearView()

    gameModel.obstacles.foreach(drawable => draw(drawable))
    gameModel.players.foreach(drawable => draw(drawable))
  }

  private def draw(visibleObject: VisibleObject): Unit = {

    visibleObject.viewModel.foreach{
      case rect: DrawableRectangle => gameCanvas.drawRectangle(visibleObject.x + rect.x, visibleObject.y + rect.y, rect.width, rect.height, rect.background, rect.border, rect.rotation)
    }

  }
}
