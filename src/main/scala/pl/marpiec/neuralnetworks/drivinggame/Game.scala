package pl.marpiec.neuralnetworks.drivinggame

import javafx.animation.AnimationTimer
import javafx.scene.canvas.Canvas

import pl.marpiec.neuralnetworks.KeyboardState


class Game(val canvas: Canvas, val keyboardState: KeyboardState) {


  private val model: GameModel = GameModel.empty
  private val engine = new GameEngine(model, keyboardState)
  private val gameCanvas = new GameCanvas(canvas, model.camera)
  private val painter = new GamePainter(gameCanvas, model)

  private var startTime: Long = 0
  private var lastFrameTime: Long = 0

  private val animationTimer = new AnimationTimer {
    override def handle(now: Long): Unit = {
      val currentTime = System.currentTimeMillis()
      nextFrame(currentTime, currentTime - lastFrameTime)
      lastFrameTime = currentTime
    }
  }

  def start(): Unit = {

    initGame()
    startTime = System.currentTimeMillis()
    engine.startTime = startTime
    lastFrameTime = startTime
    animationTimer.start()

  }


  def initGame(): Unit = {

    model.addObstacle(new RectangularObstacle(5, -5, 2, 1))

    model.addObstacle(new RectangularObstacle(10, -10, 3, 2))

    model.addObstacle(new RectangularObstacle(15, -15, 3, 1))

    model.addPlayer(new Player(10, 0, 1, 2, 0, 0))

  }


  private def nextFrame(currentTime: Long, timeDelta: Long): Unit = {
    var i = 0
    while(i < timeDelta) {
      engine.nextFrame(currentTime)
      i += 1
    }
    painter.paint()
  }



}
