package pl.marpiec.neuralnetworks

import java.awt.image.BufferedImage
import java.lang.Boolean
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.collections.{FXCollections, ObservableList}
import javafx.embed.swing.SwingFXUtils
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control.{Button, ListView, TextArea}
import javafx.scene.image.ImageView
import javafx.scene.input.{KeyEvent, MouseEvent}
import javafx.scene.layout.{BorderPane, Pane, StackPane, VBox}
import javafx.scene.paint.Color
import javafx.scene.shape.{Circle, Rectangle}
import javafx.scene.{Node, Parent, Scene}

case class ChangeEvent[T](observable: ObservableValue[_ <: T], oldValue: T, newValue: T)

object JavaFxBuilder {

  def Scene(parent: Parent, width: Double, height: Double, fillColor: Color = null,onKeyPressed: KeyEvent => Unit = null): Scene = {
    val scene = new Scene(parent, width, height)
    if(fillColor != null) scene.setFill(fillColor)
    if(onKeyPressed != null) {
      scene.setOnKeyPressed(new EventHandler[KeyEvent] {
        override def handle(event: KeyEvent): Unit = onKeyPressed(event)
      })
    }
    scene
  }

  def BorderPane(center: Node = null, top: Node = null, right: Node = null, bottom: Node = null, left: Node = null): BorderPane = {
    val pane = new BorderPane()
    if(center != null) pane.setCenter(center)
    if(top != null) pane.setTop(top)
    if(right != null) pane.setRight(right)
    if(bottom != null) pane.setBottom(bottom)
    if(left != null) pane.setLeft(left)
    pane
  }

  def StackPane(style: String = null)(children: Node*): StackPane = {
    val stackPane = new StackPane(children:_*)
    if(style != null) stackPane.setStyle(style)
    stackPane
  }

  def Pane(style: String = null, onMouseClicked: MouseEvent => Unit = null)(children: Node*): Pane = {
    val pane = new Pane(children:_*)
    if(style != null) pane.setStyle(style)
    pane.setOnMouseClicked(new EventHandler[MouseEvent] {
      override def handle(event: MouseEvent): Unit = onMouseClicked(event)
    })

    pane.setFocusTraversable(true)
    pane
  }



  def Button(text: String,
            onAction: ActionEvent => Unit = null): Button = {
    val btn = new Button()
    btn.setText(text)
    if(onAction != null) {
      btn.setOnAction(new EventHandler[ActionEvent] {
        override def handle(event: ActionEvent): Unit = onAction(event)
      })
    }
    btn
  }

  def ListView[T](items: ObservableList[T] = FXCollections.observableArrayList[T]()): ListView[T] = {
    new ListView[T](items)
  }

  def TextArea(text: String, onChange: ChangeEvent[String] => Unit = null, onFocusChange: ChangeEvent[Boolean] => Unit = null): TextArea = {
    val textArea = new TextArea(text)
    textArea.setPrefColumnCount(50)
    textArea.setPrefRowCount(20)
    textArea.setWrapText(false)
    if(onFocusChange != null) {
      textArea.focusedProperty().addListener(new ChangeListener[Boolean] {
        override def changed(observable: ObservableValue[_ <: Boolean], oldValue: Boolean, newValue: Boolean): Unit = onFocusChange(ChangeEvent(observable, oldValue, newValue))
      })
    }
    if(onChange != null) {
      textArea.textProperty().addListener(new ChangeListener[String] {
        override def changed(observable: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit = onChange(ChangeEvent(observable, oldValue, newValue))
      })
    }
    textArea
  }

  def Rectangle(x: Double, y: Double, width: Double, height: Double, fill: Color = null,
                onMousePressed: MouseEvent => Unit = null,
                onDragged: MouseEvent => Unit = null): Rectangle = {
    val rectange = new Rectangle(x, y, width, height)
    if(fill!=null) rectange.setFill(fill)
    if(onMousePressed != null) {
      rectange.setOnMousePressed(new EventHandler[MouseEvent] {
        override def handle(event: MouseEvent): Unit = onMousePressed(event)
      })
    }
    if(onDragged != null) {
      rectange.setOnMouseDragged(new EventHandler[MouseEvent] {
        override def handle(event: MouseEvent): Unit = onDragged(event)
      })
    }
    rectange
  }

  def Circle(centerX: Double, centerY: Double, radius: Double, id: String = null, fill: Color = null,
             onDragDetected: MouseEvent => Unit = null, onDragged: MouseEvent => Unit = null): Circle = {
    val circle = new Circle(radius)
    if(id != null) circle.setId(id)
    if(fill!=null) circle.setFill(fill)
    if(onDragDetected != null) {
      circle.setOnDragDetected(new EventHandler[MouseEvent] {
        override def handle(event: MouseEvent): Unit = onDragDetected(event)
      })
    }
    if(onDragged != null) {
      circle.setOnMouseDragged(new EventHandler[MouseEvent] {
        override def handle(event: MouseEvent): Unit = onDragged(event)
      })
    }
    circle
  }

  def ImageView(image: BufferedImage, x: Int, y: Int): ImageView = {
    val imageView = new ImageView(SwingFXUtils.toFXImage(image, null))
    imageView.setX(x)
    imageView.setY(y)
    imageView
  }

  def VBox()(children: Node*): VBox = {
    val vBox = new VBox(children:_*)
    vBox
  }

}
