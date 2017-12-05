package wireframe

import scala.collection.mutable.ListBuffer

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * Paragraph: An element which contains text, and the text can be aligned
  * Final, as nothing should extend this.
  *
  * @param _linearProperty the linear property of the paragraph
  * @param _locked the status indicating of a paragraph is locked
  * @param _annotations the list of annotations relating to the paragraph
  * @param _alignment the alignment of the paragraph
  * @param _paragraphs the list of paragraphs contained by the paragraph
  */
final class Paragraph (private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer(),
	private var _alignment: String = "Left",
	private val _paragraphs: ListBuffer[Paragraph] = ListBuffer())
	extends Text(_linearProperty, _locked, _annotations, "Blocked", _alignment) {
}

object Paragraph{
	final val TYPE: String = "Paragraph" // The type of element we have
}
