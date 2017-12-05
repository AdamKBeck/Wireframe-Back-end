package wireframe

import scala.collection.mutable.ListBuffer

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * RoundedBox: A type of box that's rounded and can be placed on the canvas
  * Final, as nothing should extend this
  *
  * @param _linearProperty the linear properties that the box follows
  * @param _locked  the status indicating if the box is locked
  * @param _annotations  the list of annotations relating to the box
  */
final class RoundedBox (private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer())
	extends Box(_linearProperty, _locked, _annotations) {
}

object RoundedBox {
	final val TYPE: String = "RoundedBox" // The type of element we have
}
