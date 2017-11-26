/* Provides information and functionalities for an element's group. Groups can be annotated
 * Note: An element is always in a group with itself
 */

import scala.collection.mutable.ListBuffer

final case class Group private(private val _elements: ListBuffer[Element] = ListBuffer(),
	private val _annotations: ListBuffer[Annotation] = ListBuffer()) {

	// Public getters
	def elements: List[Element] = _elements.toList // Defensive copy
	def annotations: List[Annotation] = _annotations.toList // Defensive copy

	// Public functions
	// Add/remove elements to the group
	def add(element: Element): Unit = _elements += element
	def remove(element: Element): Unit = _elements -= element

	// Add/remove elements from groups to the group (subgrouping)
	def add(group: Group): Unit = _elements ++= group.elements
	def remove(group: Group): Unit = group.elements.map(_elements -= _)

	// add/remove annotations to this group as a whole
	def annotate(annotation: Annotation): Unit = _annotations += Annotation(message)
	def deannotate(annotation: Annotation): Unit = _annotations -= annotation
}
