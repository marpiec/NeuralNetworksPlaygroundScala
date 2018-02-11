package pl.marpiec.neuralnetworks

sealed trait ApplicationEvent

case class ViewModelChanged() extends ApplicationEvent
