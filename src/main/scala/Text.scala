/* Provides information and functionalities for Text */

package wireframe

import scala.collection.mutable.ListBuffer

// Note the protected constructor similar to the Element class
class Text protected (private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer(),
	private val _textType: String = "Blocked",
	private var _alignment: String = "Left")
	extends Element(_linearProperty, _locked, _annotations) {

	// Public getters
	def text: Option[String] = Text.textCache.get(_textType)

	def alignment: String = _alignment

	// Public setters
	def alignment_=(value: String): Unit = _alignment = value

	// "The text cannot be edited" so there's no setter for _textType
}

object Text {
	final val ALIGNMENTS = Set("Left", "Right", "Center", "Justified")
	final val textCache = Map("Canned" -> "Lorem Ipsum....",
		"Blocked" -> "BLOCKED....")


}

