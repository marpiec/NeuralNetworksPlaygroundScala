package pl.marpiec.neuralnetworks.drivinggame

import pl.marpiec.neuralnetworks.KeyboardState

class GameEngine(model: GameModel, keyboardState: KeyboardState) {

  var startTime: Long = 0

  def start(now: Long): Unit = {
    startTime = now
  }


  def nextFrame(now: Long): Unit = {

    val playerInput = new PlayerInput(keyboardState.left, keyboardState.right, keyboardState.up, keyboardState.down)

    model.obstacles.foreach {
      case o: RectangularObstacle => updateRectangularObstacle(o, now)
    }

    model.players.foreach(player => updatePlayer(player, playerInput))

    updateCamera()
  }

  private def updateRectangularObstacle(o: RectangularObstacle, now: Long): Unit = {
//    val cycle = (now - startTime).toDouble / 1000.0 * Math.PI
    // Do nothing
  }

  private def updatePlayer(player: Player, playerInput: PlayerInput): Unit = {

    if(playerInput.accelerate) {
      player.speed = Math.min(player.speed + 0.0001, 0.01)
    }

    if(playerInput.breaking) {
      player.speed = Math.max(player.speed - 0.0001, - 0.002)
    }

    if(playerInput.turnLeft) {
      player.direction -= 0.002
    }

    if(playerInput.turnRight) {
      player.direction += 0.002
    }

    player.x += player.speed * Math.sin(player.direction)
    player.y -= player.speed * Math.cos(player.direction)

  }

  private def updateCamera(): Unit = {

    var topPlayer = model.players.head

    model.players.foreach(player => {
      if(player.y > topPlayer.y) {
        topPlayer = player
      }
    })

    model.camera.y = topPlayer.y - 18

  }



}
