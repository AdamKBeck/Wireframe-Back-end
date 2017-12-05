package wireframe

import scala.collection.mutable.ListBuffer

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * Element: An element is one of the many types of things we can place on a Canvas. Elements
  * have linear properties and can be grouped together, and can be annotated.
  * Abstract, as only subclasses should have objects being created from them. Element doesn't exist on its own.
  *
  * @param _linearProperty the linear property relating to the element
  * @param _locked the status indicating of an element is locked
  * @param _annotations a list of annotations relating to the element
  */
abstract class Element (private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer())
	extends Lockable{

	/* Accessor to the barricade, either the locked or default barricade depending on
	 * if the element is locked or not, respectively */
	var barricadeInstance: WireframeBarricade = WireframeBarricade.instance

	/**
	  * Sets the barricade accessor to be of the locked type
	  */
	def setLockedBarricadeState(): Unit = barricadeInstance = WireframeBarricadeLocked.instance

	/**
	  * Sets the barricade accessor to be of the default, unlocked type
	  */
	def setUnlockedBarricadeState(): Unit = barricadeInstance = WireframeBarricade.instance

	/**
	  * Gets the linear property of an element
	  *
	  * @return a LinearProperty object representing the element's linear property
	  */
	def linearProperty: LinearProperty = _linearProperty

	/**
	  * Gets the locked state of an element
	  *
	  * @return a boolean representing if an element is locked
	  */
	def locked: Boolean = _locked

	/**
	  * Gets a list of annotations relating to the element
	  *
	  * @return a list of annotations relating to this element
	  */
	def annotations: List[Annotation] = _annotations.toList // Defensive copy as a list is immutable

	/**
	  * Sets the locked value for the element
	  *
	  * @param value the locked value to set for an element
	  */
	def locked_=(value: Boolean): Unit = {
		// Changes the accessor to the type of barricade that matched if an element is locked or not
		if (value) setLockedBarricadeState() else setUnlockedBarricadeState()
		_locked = value
	}

	/**
	  * Adds an annotation to the element
	  *
	  * @param message the message to add to an annotation for the element
	  */
	def annotate(message: String): Unit = {
		_annotations += Annotation(_linearProperty = linearProperty, _message = message)
	}

	/**
	  * Removes an annotation for the element
	  *
	  * @param index The index of an annotation to remove from the element
	  */
	def deannotate(index: Int): Unit = _annotations.remove(index)

	/**
	  * Gets this element's width
	  *
	  * @return the element's width in pixels
	  */
	def width: Int = _linearProperty.width

	/**
	  * Gets this element's height
	  *
	  * @return the element's width in location
	  */
	def height: Int = _linearProperty.height

	/**
	  * Gets this element's location
	  *
	  * @return the location of the element
	  */
	def location: Location = _linearProperty.location

	/**
	  * Gets this element's layerPriority
	  *
	  * @return the layer priority of the element, represented as a Byte
	  */
	def layerPriority: Byte = _linearProperty.layerPriority

	/**
	  * Sets the element's width
	  *
	  * @param value the value to set the element's width, in pixels
	  */
	def width_=(value: Int): Unit = _linearProperty.width = value

	/**
	  * Sets the element's height
	  *
	  * @param value the value to set the element's height, in pixels
	  */
	def height_=(value: Int): Unit = _linearProperty.height = value

	/**
	  * Sets the element's layer priority
	  * @param priority the priority to set the element's layer priority, in bytes
	  */
	def layerPriority_=(priority: Byte): Unit = _linearProperty.layerPriority = priority

	/**
	  * Brings an element to the top in the canvas
	  */
	def bringToTop(): Unit = _linearProperty.bringToTop()

	/**
	  * Brings an element to the bottom in the canvas
	  */
	def bringToBottom(): Unit = _linearProperty.bringToBottom()

	/**
	  * Gets the element's x position in the canvas
	  *
	  * @return An int representing the element's x coordinate in a canvas
	  */
	def x: Int = _linearProperty.x

	/**
	  * Gets the element's y position in the canvas
	  *
	  * @return An int representing the element's y coordinate in a canvas
	  */
	def y: Int = _linearProperty.y

	/**
	  * Sets the element's x position in the canvas
	  *
	  * @param coordinate the value to set the element's x position to, in pixels
	  */
	def x_=(coordinate: Int): Unit = _linearProperty.x = coordinate

	/**
	  * Sets the element's y position in the canvas
	  *
	  * @param coordinate the value to set the element's y position to, in pixels
	  */
	def y_=(coordinate: Int): Unit = _linearProperty.y = coordinate

	/**
	  * Gets the visiblity of an annotaion for the elemnet
	  *
	  * @param index the index of the annotation to get the visibility for
	  * @return the visibility of an annotation for the element, represented as a boolean
	  */
	def annotationVisibility(index: Int): Boolean = _annotations(index).visibility

	/**
	  * Gets the linear property of an annotaion for the elemnet
	  *
	  * @param index the index of the linear property to get the visibility for
	  * @return the visibility of an linear property for the element, represented as a boolean
	  */
	def annotationLinearProperty(index: Int): LinearProperty = _annotations(index).linearProperty

	/**
	  * Gets the annotation message for an element
	  *
	  * @param index the index of the annotation message to get for the element
	  * @return a string representing the annotatino message for the element
	  */
	def annotationMessage(index: Int): String = _annotations(index).message

	/**
	  * Sets the annotation visibility for an element
	  *
	  * @param index the index of the annotation to set for the element
	  * @param value the value of the annotation visiblity, represented as a boolean
	  */
	def setAnnotationVisibility(index: Int, value: Boolean): Unit = _annotations(index).visibility = value

	/**
	  * Sets the annotation message for an element
	  *
	  * @param index the index of the annotation to set for the element
	  * @param newMessage the message of the annotation to set to, represented as a string
	  */
	def setAnnotationMessage(index: Int, newMessage: String): Unit = _annotations(index).message = newMessage
}
