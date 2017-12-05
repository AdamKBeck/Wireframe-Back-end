package wireframe

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * LinearProperty: Provides 2D shape and size info and resizing.
  * Final, as nothing should extend this
  *
  * @param _width the width of the object associated to the linear property
  * @param _height the height of the object associated to the linear property
  * @param _location the location of the element associated to the linear property
  * @param _layerPriority the layer priority of the object associated to the linear property, represented as a byte
  */
final case class LinearProperty private(private var _width: Int,
	private var _height: Int,
	private val _location: Location,
	private var _layerPriority: Byte) {

	/**
	  * Gets the width for the linear property
	  *
	  * @return the width of the linear property in pixels
	  */
	def width: Int = _width

	/**
	  * Gets the height for the linear property
	  *
	  * @return the height of the linear property in pixels
	  */
	def height: Int = _height

	/**
	  * Gets the location for the linear property
	  *
	  * @return the location of the linear property
	  */
	def location: Location = _location

	/**
	  * Gets the layerPriority for the linear property
	  *
	  * @return the layerPriority of the linear property, represented as a Byte
	  */
	def layerPriority: Byte = _layerPriority

	/**
	  * Sets the width of the linear property
	  *
	  * @param value the value of which to set the width to for the linear property
	  */
	def width_=(value: Int): Unit = _width = value

	/**
	  * Sets the height of the linear property
	  *
	  * @param value the value of which to set the height to for the linear property
	  */
	def height_=(value: Int): Unit = _height = value

	/**
	  * Sets the layer priority for the linear property
	  *
	  * @param priority the value to set the layer priority to, represented as a byte
	  */
	def layerPriority_=(priority: Byte): Unit = _layerPriority = priority

	/**
	  * Sets the element to the top of the other elements
	  */
	def bringToTop(): Unit = _layerPriority = LinearProperty.TOP

	/**
	  * Sets the element to the bottom of the other elements
	  */
	def bringToBottom(): Unit = _layerPriority = LinearProperty.BOTTOM

	/**
	  * Gets the x-coordinate of the object using the linear property
	  *
	  * @return the x coordinate of the object using the linear property, in pixels
	  */
	def x: Int = _location.x

	/**
	  * Gets the y-coordinate of the object using the linear property
	  *
	  * @return the y coordinate of the object using the linear property, in pixels
	  */
	def y: Int = _location.y

	/**
	  * Sets the x-coordinate of the object using the linear property
	  *
	  * @param coordinate the coordinate, represented as a pixel Int, to set the object to
	  */
	def x_=(coordinate: Int): Unit = _location.x = coordinate

	/**
	  * Sets the y-coordinate of the object using the linear property
	  *
	  * @param coordinate the coordinate, represented as a pixel Int, to set the object to
	  */
	def y_=(coordinate: Int): Unit = _location.y = coordinate
}

object LinearProperty {
	final val BOTTOM: Byte = -128 // The lowest layer an object using the linear property can have
	final val TOP: Byte = 127 // The highest layer an object using the linear property can have
	final val DEFAULT_WIDTH: Int = 0 // The default width of the object using the linear property, in pixels
	final val DEFAULT_HEIGHT: Int = 0// The default height of the object using the linear property, in pixels

	// the default linear property of an object using the linear property
	final val DEFAULT_LINEAR_PROPERTY: LinearProperty = LinearProperty(DEFAULT_WIDTH,
		DEFAULT_HEIGHT,
		Location(Location.DEFAULT_X_LOCATION, Location.DEFAULT_Y_LOCATION),
		TOP)
}
