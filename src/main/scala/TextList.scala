// List might interfere with the List keyword so I renamed this class TextList

package wireframe

import scala.collection.mutable.ListBuffer

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * TextList: A type of Text that contains a text type, alignment, and paragraphs for
  * each bullet point in the text.
  * Final, as nothing should textend this.
  *
  * @param _linearProperty the linear property relating to the element
  * @param _locked the status indicating of an element is locked
  * @param _annotations a list of annotations relating to the element
  * @param _textType the type of text, canned or blocked, represented as a string, the text contains
  * @param _alignment the type of alignment for the text, left right center or justified
  * @param _paragraphs the list of paragraphs, each paragraph is a bullet point in the text
  */
final class TextList (private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer(),
	private val _textType: String = "Blocked",
	private var _alignment: String = "Left",
	private val _paragraphs: ListBuffer[Paragraph] = ListBuffer())
	extends Text(_linearProperty, _locked, _annotations, _textType, _alignment) {

	/**
	  * Gets the paragraphs for the textlist
	  *
	  * @return a list of paragraphs, represnting the bullet points, for the textlist
	  */
	def paragraphs: List[Paragraph] = _paragraphs.toList // Defensive copy

	/**
	  * Adds a paragraph to the textlist
	  *
	  * @param paragraph the paragraph to add to the textlist
	  */
	def add(paragraph: Paragraph): Unit = _paragraphs += paragraph

	/**
	  * Removes a paragraph from the textlist
	  *
	  * @param index the index of the paragraph to remove from the textlist
	  */
	def remove(index: Int): Unit = _paragraphs.remove(index)
}

object TextList {
	final val TYPE: String = "TextList" // The type of the element
}
