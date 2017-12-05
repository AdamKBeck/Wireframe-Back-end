package wireframe

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * Location: A location on the canvas that an object contains
  * Final, as nothing should extend this class
  *
  * @param _x the x coordinate of the location, in pixels
  * @param _y the y coordinate of the location, in pixels
  */
final case class Location (private var _x: Int, private var _y: Int) {
	/**
	  * Gets the x-coordinate of the object using the location
	  *
	  * @return the x coordinate of the object using the location, in pixels
	  */
	def x: Int = _x

	/**
	  * Gets the y-coordinate of the object using the location
	  *
	  * @return the y coordinate of the object using the location, in pixels
	  */
	def y: Int = _y

	/**
	  * Sets the x-coordinate of the object using the location
	  *
	  * @param coordinate the coordinate, represented as a pixel Int, to set the object to
	  */
	def x_=(coordinate: Int): Unit = _x = coordinate

	/**
	  * Sets the y-coordinate of the object using the location
	  *
	  * @param coordinate the coordinate, represented as a pixel Int, to set the object to
	  */
	def y_=(coordinate: Int): Unit = _y = coordinate
}

object Location {
	final val DEFAULT_X_LOCATION: Int = 0 // The default x-location of the object having the location, in pixels
	final val DEFAULT_Y_LOCATION: Int = 0 // The default y-location of the object having the location, in pixels
}
