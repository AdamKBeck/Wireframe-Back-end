package wireframe

import scala.collection.mutable.ListBuffer

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * Group: A group has a list of elements that make up the group. The group can be commented.
  * Final: As nothing should extend this
  *
  * @param _elements the list of elements in the group
  * @param _annotations the list of annotations for the group
  * @param _locked the status indiciating if the group is locked
  */
final case class Group (private val _elements: ListBuffer[Element] = ListBuffer(),
	private val _annotations: ListBuffer[Annotation] = ListBuffer(),
	private var _locked: Boolean = false)
	extends Lockable{

	/**
	  * Gets the elements in the group
	  *
	  * @return the list of elements representing each element in the group
	  */
	def elements: List[Element] = _elements.toList // Defensive copy

	/**
	  * Gets the annotation for the group
	  *
	  * @return the list of annotations for the group
	  */
	def annotations: List[Annotation] = _annotations.toList // Defensive copy

	/**
	  * Gets the locked status for the group
	  *
	  * @return a boolean representing the group's locked status
	  */
	def locked: Boolean = _locked

	/**
	  * Sets the locked status for the group
	  *
	  * @param value the boolean value representing the locked status to set the group to
	  */
	def locked_=(value: Boolean): Unit = _locked = value

	/**
	  * Adds an element to the group
	  *
	  * @param element the element to add to the group
	  */
	def add(element: Element): Unit = _elements += element

	/**
	  * Removes an element from the group
	  *
	  * @param element the element to remove from the group
	  */
	def remove(element: Element): Unit = _elements -= element

	/**
	  * Adds an annotation for the group
	  *
	  * @param annotation the annotation to add to the group
	  */
	def annotate(annotation: Annotation): Unit = _annotations += annotation

	/**
	  * Removes an annotation for the group
	  *
	  * @param annotation the annotation to remove from the group
	  */
	def deannotate(annotation: Annotation): Unit = _annotations -= annotation

	/**
	  * Gets the visiblity of an annotation for the group
	  *
	  * @param index the index of the annotation to get the visibility for
	  * @return the visibility of the annotation represnted by a boolean
	  */
	def annotationVisibility(index: Int): Boolean = _annotations(index).visibility

	/**
	  * Gets the linear property for an annotation for a group
	  *
	  * @param index the index of the annotatino to get linear property from
	  * @return the linear property of an annotation in the group
	  */
	def annotationLinearProperty(index: Int): LinearProperty = _annotations(index).linearProperty

	/**
	  * Gets the message for an annotation in the group
	  *
	  * @param index the index of the annotation to get for the group
	  * @return a string representing the message for the annotation for the group
	  */
	def annotationMessage(index: Int): String = _annotations(index).message

	/**
	  * Sets the visibility of an annotation for the group
	  * @param index the index to set the visibility for of an annotation
	  * @param value the boolean value representing the visibilty to set for an annotation
	  */
	def setAnnotationVisibility(index: Int, value: Boolean): Unit = _annotations(index).visibility = value

	/**
	  * Sets a message of an annotation for the group
	  * @param index the index to set the annotaion message for a group
	  * @param newMessage the message represented as a string of the annotation
	  */
	def setAnnotationMessage(index: Int, newMessage: String): Unit = _annotations(index).message = newMessage
}
