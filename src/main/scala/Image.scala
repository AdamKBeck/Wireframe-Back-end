package wireframe

import scala.collection.mutable.ListBuffer

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * Image: A type of element that can be placed on the canvas.
  * Final, as nothing should override thid
  *
  * @param _linearProperty the linear property relating to an image
  * @param _locked  the status indicating if the box is locked
  * @param _annotations  the list of annotations relating to the box
  */
final class Image (private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer())
	extends Box(_linearProperty, _locked, _annotations) {
}

object Image {
	// The type of element we have
	final val TYPE: String = "Image"
}
