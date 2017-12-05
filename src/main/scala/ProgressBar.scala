package wireframe

import scala.collection.mutable.ListBuffer

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * ProgressBar: a type of bar to place on the canvas
  * Final, as nothing should extend this.
  *
  * @param _linearProperty the linear properties that the bar follows
  * @param _locked the status indicating if this bar is locked
  * @param _annotations the list of annotations relating to the bar
  * @param _value the value (or percentage) of the bar
  */
final class ProgressBar (private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer(),
	private var _value: Int = Bar.MINIMUM_VALUE)
	extends Bar(_linearProperty, _locked, _annotations, _value) {

}

object ProgressBar {
	final val TYPE: String = "ProgressBar" // What type of element we have
}

