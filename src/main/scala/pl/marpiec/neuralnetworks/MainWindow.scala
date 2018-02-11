package pl.marpiec.neuralnetworks

import java.time.Clock
import javafx.application.Application
import javafx.scene.paint.Color
import javafx.stage.{Stage, StageStyle}
import JavaFxBuilder._


object MainWindow extends App {
  Application.launch(classOf[MainWindow])
}

class MainWindow extends Application {


  private val clock = Clock.systemDefaultZone()

  private val viewModel = new ViewModel()
  private val eventBus = new EventBus
  private val commandBus = new CommandBus(viewModel, eventBus)

  override def start(stage: Stage): Unit = {

    println("Initialized")
    showMainWindow(stage)

  }


  private def showMainWindow(stage: Stage): Unit = {
    stage.initStyle(StageStyle.DECORATED)

    val scene = Scene(
      width = 600,
      height = 400,
      fillColor = Color.TRANSPARENT,
      parent = BorderPane(
        center =  TextArea("Center box"),
        left = VBox()(
          Button(
            text = "Do something",
            onAction = event => commandBus.doSomething()
          )
        ),
        right = TextArea("Right box")
      )
    )

    stage.setTitle("Neural networks!")
    stage.setScene(scene)
    stage.show()
  }

}
