
package wireframe

import scala.collection.mutable.ListBuffer

/**
  * Final, as nothing should extend this.
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * Provides information and functionalities for the Canvas, the "webpage" each element of the
  * wireframe is being drawn into.
  * Final, as no class should extend this.
  * Singleton, as we only want one canvas.
  *
  * @param _width the width of the canvas in pixels
  * @param _height the height of the canvas in pixels
  */
final case class Canvas private(private val _width: Int,
	private val _height: Int){

	private val _elements: ListBuffer[Element] = ListBuffer() // The list of elements on the canvas
	private val _groups: ListBuffer[Group] = ListBuffer() // The list of groups on the canvas
	private val _annotations: ListBuffer[Annotation] = ListBuffer() // The list of annotations on a group/element on the canvas

	/**
	  * Gets the elements on the canvas
	  *
	  * @return a list of elements on the canvas
	  */
	def elements: List[Element] = _elements.toList // Defensive copy

	/**
	  * Gets the groups on the canvas
	  *
	  * @return a list of groups on the canvas
	  */
	def groups: List[Group] = _groups.toList // Defensive copy

	/**
	  * Gets the annotations assocated with elements/groups on the canvas
	  *
	  * @return a list of annotations on the canvas
	  */
	def annotations: List[Annotation] = _annotations.toList // Defensive copy

	/**
	  * Gets the height of the canvas
	  *
	  * @return the height of the canvas in pixels
	  */
	def height: Int = _height

	/**
	  * Gets the width of the canvas
	  *
	  * @return the width of the canavs, in pixels
	  */
	def width: Int = _width

	/**
	  * Adds an element to the canvas
	  *
	  * @param element the element to add to the canvas
	  */
	def add(element: Element): Unit = _elements += element

	/**
	  * Removes an element from the canvas
	  *
	  * @param element the element to remove from the canvas
	  */
	def remove(element: Element): Unit = _elements -= element

	/**
	  * Adds a group to the canvas
	  *
	  * @param group the group to add to the canvas
	  */
	def add(group: Group): Unit = _groups += group

	/**
	  * Removes a group from the canvas
	  *
	  * @param group the group to remove from the canvas
	  */
	def remove(group: Group): Unit = _groups -= group

	/**
	  * Adds an annotation to the canvas
	  *
	  * @param annotation the annotation to add to the canvas
	  */
	def add(annotation: Annotation): Unit = _annotations += annotation

	/**
	  * Removes an annotation from the canvas
	  *
	  * @param annotation the annotation to remove from the canvas
	  */
	def remove(annotation: Annotation): Unit = _annotations -= annotation

	/**
	  * Clears the canvas to a blank, starting state
	  */
	def clear() = {
		_elements.clear()
		_groups.clear()
		_annotations.clear()
	}
}

object Canvas {
	final val DEFAULT_WIDTH: Int = 800 // The default width of a canvas in pixels
	final val DEFAULT_HEIGHT: Int = 800 // The default height of a canvas in pixels

	// Singleton implementation
	private val _instance = Canvas(DEFAULT_WIDTH, DEFAULT_HEIGHT)
	def instance: Canvas = _instance

}
