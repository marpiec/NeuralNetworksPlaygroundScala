package pl.marpiec.neuralnetworks


class CommandBus(model: ViewModel, eventBus: EventBus) {
  def doSomething(): Unit = {
    println("Do something")
  }

}
