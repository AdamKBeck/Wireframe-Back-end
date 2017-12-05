package wireframe

import scala.collection.mutable.ListBuffer

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * @param _linearProperty the linear properties that the bar follows
  * @param _locked the status indicating if this bar is locked
  * @param _annotations the list of annotations relating to the bar
  * @param _value the value (or percentage) of the bar
  */
abstract class Bar(private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer(),
	private var _value: Int = Bar.MINIMUM_VALUE)
	extends Element(_linearProperty, _locked, _annotations) {

	/**
	  * Gets the value of a bar
	  *
	  * @return an int representing the bar's value/percentage
	  */
	def value: Int = _value

	/**
	  * Sets the value of a bar's value/percentage
	  *
	  * @param percentage an int representing the bar's value/percentage
	  */
	def value_=(percentage: Int): Unit = _value = percentage

}

object Bar {
	final val MINIMUM_VALUE = 0 // The minimum value/percentage a bar can have
	final val MAXIMUM_VALUE = 100 // The maximum value/percentage a bar can have
}
