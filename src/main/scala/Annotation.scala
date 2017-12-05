package wireframe

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * Annotation: A type of text that can be related to an element or a group
  * Final, as nothing should extend this class.
  *
  *
  * @param _visibility the visibility of the comment
  * @param _linearProperty the linear properties that the comment follows from its related element
  * @param _message the message the comment contains
  */
final case class Annotation(private var _visibility: Boolean = false,
	private val _linearProperty: LinearProperty,
	private var _message: String = "") {

	/**
	  * Gets the visibility of a comment
	  *
	  * @return a boolean representing the comment's visibility
	  */
	def visibility: Boolean = _visibility

	/**
	  * Gets the linear property of a comment
	  *
	  * @return a LinearProperty object representing the comment's linear property
	  */
	def linearProperty: LinearProperty = _linearProperty

	/**
	  * Gets the message the comment contains
	  *
	  * @return a string representing the comment's message
	  */
	def message: String = _message

	/**
	  * Sets the visibility for a comment
	  *
	  * @param value a boolean representing the visibility to set for a comment
	  */
	def visibility_=(value: Boolean): Unit = _visibility = value

	/**
	  * Sets the message for a comment
	  *
	  * @param newMessage a string representing the message to set for a comment
	  */
	def message_=(newMessage: String): Unit = _message = newMessage

}
