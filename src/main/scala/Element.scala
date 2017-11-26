/* Provides information and functionalities for an Element. An element is one of the many
 * types of objects we can place on a canvas.
 */

import scala.collection.mutable.ListBuffer

case class Element(private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer())
	extends Lockable{

	// Public getters
	def linearProperty: LinearProperty = _linearProperty
	def locked: Boolean = _locked
	def annotations: List[Annotation] = _annotations.toList // Defensive copy as a list is immutable

	// Public setters
	def locked_=(value: Boolean): Unit = _locked = value

	// Public functions
	// Adds an annotation to this element. the annotation's linear property is the element's
	def annotate(message: String): Unit = {
		_annotations += Annotation(_linearProperty = linearProperty, _message = message)
	}

	// Removes an annotation by a specific index for an element
	def deannotate(index: Int): Unit = _annotations.remove(index)


	//TODO: methods to follow law of demeter (set a new location, get positions, etc?)
}
