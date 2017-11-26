/* Provides information and functionalities for an Element. An element is one of the many
 * types of objects we can place on a canvas.
 */

import scala.collection.mutable.ListBuffer

case class Element(private val _linearProperty: LinearProperty = LinearProperty.instance,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer()) {

	// Public getters
	def linearProperty: LinearProperty = _linearProperty
	def locked: Boolean = _locked
	def annotations: List[Annotation] = _annotations.toList // Defensive copy as a list is immutable

	// Public setters
	def locked_=(value: Boolean): Unit = _locked = value

	//TODO: methods to follow law of demeter (set a new location?)
}

object Element {
	def main(args: Array[String]): Unit = {
	}
}
