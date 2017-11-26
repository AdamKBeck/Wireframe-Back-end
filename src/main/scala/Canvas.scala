/* Provides information and functionalities for the Canvas, the "webpage" each element of the
 * wireframe is being drawn into. Final, as no class should extend this.
 *
 * Singleton, as we only want one canvas.
 */

import scala.collection.mutable.ListBuffer

final case class Canvas private(private val _width: Int,
	private val _height: Int){

	// A canvas contains elements and groups and annotations for them
	private val _elements: ListBuffer[Element] = ListBuffer()
	private val _groups: ListBuffer[Group] = ListBuffer()
	private val _annotations: ListBuffer[Annotation] = ListBuffer()

	// Public getters
	def elements: List[Element] = _elements.toList // Defensive copy
	def groups: List[Group] = _groups.toList // Defensive copy
	def annotations: List[Annotation] = _annotations.toList // Defensive copy
	def height: Int = _height
	def width: Int = _width

	// Public functions
	// Adds/Removes an element to the canvas
	def add(element: Element): Unit = _elements += element
	def remove(element: Element): Unit = _elements -= element

	// Adds/Removes a group to the canvas (all elements must be present on canvas for a group to be added)
	def add(group: Group): Unit = _groups += group
	def remove(group: Group): Unit = _groups -= group

	// Adds/removes annotations to the canvas
	def add(annotation: Annotation): Unit = _annotations += annotation
	def remove(annotation: Annotation): Unit = _annotations -= annotation
}

object Canvas {
	// Private static fields
	private final val DEFAULT_WIDTH: Int = 800
	private final val DEFAULT_HEIGHT: Int = 800

	// Singleton implementation
	private val _instance = Canvas(DEFAULT_WIDTH, DEFAULT_HEIGHT)
}
