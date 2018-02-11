package pl.marpiec.neuralnetworks

import java.time.Clock
import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.paint.Color
import javafx.stage.{Stage, StageStyle}

import JavaFxBuilder._
import pl.marpiec.neuralnetworks.drivinggame.Game


object MainWindow extends App {
  Application.launch(classOf[MainWindow])
}

class MainWindow extends Application {


  private val clock = Clock.systemDefaultZone()

  private val viewModel = new ViewModel()
  private val eventBus = new EventBus
  private val commandBus = new CommandBus(viewModel, eventBus)

  private val keyboardState = new KeyboardState

  private val drivingGame = new Game(Canvas(400, 400), keyboardState)

  override def start(stage: Stage): Unit = {

    println("Initialized")
    showMainWindow(stage)

    drivingGame.start()
  }


  private def showMainWindow(stage: Stage): Unit = {
    stage.initStyle(StageStyle.DECORATED)

    val scene = Scene(
      width = 600,
      height = 400,
      fillColor = Color.TRANSPARENT,
      parent = BorderPane(
        center = drivingGame.canvas,
        left = VBox()(
          Button(
            text = "Do something",
            onAction = event => commandBus.doSomething()
          )
        )
      )
    )

    scene.setOnKeyPressed((event: KeyEvent) => {
      event.getCode match {
        case KeyCode.UP => keyboardState.up = true
        case KeyCode.DOWN => keyboardState.down = true
        case KeyCode.LEFT => keyboardState.left = true
        case KeyCode.RIGHT => keyboardState.right = true
        case _ => ()
      }
    })

    scene.setOnKeyReleased((event: KeyEvent) => {
      event.getCode match {
        case KeyCode.UP => keyboardState.up = false
        case KeyCode.DOWN => keyboardState.down = false
        case KeyCode.LEFT => keyboardState.left = false
        case KeyCode.RIGHT => keyboardState.right = false
        case _ => ()
      }
    })

    stage.setTitle("Neural networks!")
    stage.setScene(scene)
    stage.show()
  }

}
