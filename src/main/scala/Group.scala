/* Provides information and functionalities for an element's group. Groups can be annotated
 * Note: An element is always in a group with itself
 * Final, as no class should extend this.
 */

import scala.collection.mutable.ListBuffer

final case class Group (private val _elements: ListBuffer[Element] = ListBuffer(),
	private val _annotations: ListBuffer[Annotation] = ListBuffer(),
	private var _locked: Boolean = false)
	extends Lockable{

	// Public getters
	def elements: List[Element] = _elements.toList // Defensive copy
	def annotations: List[Annotation] = _annotations.toList // Defensive copy
	def locked: Boolean = _locked

	// Public setters
	def locked_=(value: Boolean): Unit = _locked = value

	// Public functions
	// Add/remove elements to the group
	def add(element: Element): Unit = _elements += element
	def remove(element: Element): Unit = _elements -= element

	// Add/remove elements from groups to the group (subgrouping)
	def add(group: Group): Unit = _elements ++= group.elements
	def remove(group: Group): Unit = group.elements.map(_elements -= _)

	/* add/remove annotations to this group as a whole.
	 * Since there could be more than 1 element in a group, an annotaion object is passed,
	 * instead of just the message, because we don't know which element the annotation should
	 * get its linear property from.
	 */
	def annotate(annotation: Annotation): Unit = _annotations += annotation
	def deannotate(annotation: Annotation): Unit = _annotations -= annotation

	// Functions to follow law of demeter: Annotation getters
	def annotationVisibility(index: Int): Boolean = _annotations(index).visibility
	def annotationLinearProperty(index: Int): LinearProperty = _annotations(index).linearProperty
	def annotationMessage(index: Int): String = _annotations(index).message

	/* Functions to follow law of demeter: Annotation setters
	 * Since we have more than 1 paramater, instead of using a tuple, we name
	 * this function as a java-style setter
	 */
	def setAnnotationVisibility(index: Int, value: Boolean): Unit = _annotations(index).visibility = value
	def setAnnotationMessage(index: Int, newMessage: String): Unit = _annotations(index).message = newMessage
}
