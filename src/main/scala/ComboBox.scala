
package wireframe

import scala.collection.mutable.ListBuffer

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * ComboBox: a type of Element that has a list of choices to display
  * Final, as nothing should extend this
  *
  * @param _linearProperty the linear properties that the combobox follows
  * @param _locked the status indicating if a box is locked
  * @param _annotations the list of annotations relating to the combobox
  * @param _choices the list of choices a box can display
  */
final class ComboBox (private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer(),
	private val _choices: ListBuffer[String] = ListBuffer())
	extends Element(_linearProperty, _locked, _annotations) {

	/**
	  * Gets the choices displayed by the combobox
	  * @return a list of strings representing the choices for the combobox
	  */
	def choices: List[String] = _choices.toList // Defensive copy

	/**
	  * Adds a choice to the combobox
	  *
	  * @param choice the choice to add to the combobox
	  */
	def add(choice: String): Unit =	_choices += choice

	/**
	  * Removes a choice from the combobox
	  *
	  * @param index the index to remove a choice from of the combobox
	  */
	def remove(index: Int): Unit = _choices.remove(index)
}

object ComboBox{
	final val TYPE: String = "ComboBox" // The type of Element we have
}
