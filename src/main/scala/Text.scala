package wireframe

import scala.collection.mutable.ListBuffer

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * Text: A type of Element that contains textype (blocked or canned) and alignment for the text
  * Abstract, as only subclasses should have objects being created from them. Text doesn't exist on its own.
  *
  * @param _linearProperty the linear property relating to the element
  * @param _locked the status indicating of an element is locked
  * @param _annotations a list of annotations relating to the element
  * @param _textType the type of text, canned or blocked, represented as a string, the text contains
  * @param _alignment the type of alignment for the text, left right center or justified
  */
abstract class Text(private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer(),
	private val _textType: String = "Blocked",
	private var _alignment: String = "Left")
	extends Element(_linearProperty, _locked, _annotations) {

	/**
	  * Gets the text, if any, for the text
	  *
	  * @return the text, if any, for the text, represented as an option
	  */
	def text: Option[String] = Text.textCache.get(_textType)

	/**
	  * Gets the alignment for the text
	  *
	  * @return the alignment for the text represented as a string
	  */
	def alignment: String = _alignment

	/**
	  * Sets the alignment for a text
	  *
	  * @param value the alignment to set for a test, represented as a string
	  */
	def alignment_=(value: String): Unit = _alignment = value

	// "The text cannot be edited" so there's no setter for _textType
}

object Text {
	// A set of all the alignments for text
	final val ALIGNMENTS = Set("Left", "Right", "Center", "Justified")

	// A map from type of textType to the text the type contains
	final val textCache = Map("Canned" -> "Lorem Ipsum....",
		"Blocked" -> "BLOCKED....")


}

