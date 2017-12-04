/* Provides information and functionalities for an Element. An element is one of the many
 * types of objects we can place on a canvas.
 */

package wireframe

import scala.collection.mutable.ListBuffer

// Protected, as I don't want to create an element. I only want to create it from its subclasses
abstract class Element (private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
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

	// Functions to follow law of demeter: LinearProperty getters
	def width: Int = _linearProperty.width
	def height: Int = _linearProperty.height
	def location: Location = _linearProperty.location
	def layerPriority: Byte = _linearProperty.layerPriority

	// Functions to follow law of demeter: LinearProperty setters
	def width_=(value: Int): Unit = _linearProperty.width = value
	def height_=(value: Int): Unit = _linearProperty.height = value
	def layerPriority_=(priority: Byte): Unit = _linearProperty.layerPriority = priority
	def bringToTop(): Unit = _linearProperty.bringToTop()
	def bringToBottom(): Unit = _linearProperty.bringToBottom()

	// Functions to follow law of demeter: Location getters
	def x: Int = _linearProperty.x
	def y: Int = _linearProperty.y

	// Functions to follow law of demeter: Location setters
	def x_=(coordinate: Int): Unit = _linearProperty.x = coordinate
	def y_=(coordinate: Int): Unit = _linearProperty.y = coordinate

	// Functions to follow law of demeter: Annotation getters
	def annotationVisibility(index: Int): Boolean = _annotations(index).visibility
	def annotationLinearProperty(index: Int): LinearProperty = _annotations(index).linearProperty
	def annotationMessage(index: Int): String = _annotations(index).message

	/* Functions to follow law of demeter: Annotation setters
	 * Since we have more than 1 paramater, instead of using a tuple, we name
	 * this function as a java-style setter */
	def setAnnotationVisibility(index: Int, value: Boolean): Unit = _annotations(index).visibility = value
	def setAnnotationMessage(index: Int, newMessage: String): Unit = _annotations(index).message = newMessage
}
