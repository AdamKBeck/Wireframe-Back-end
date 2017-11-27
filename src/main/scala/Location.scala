// Provides location information of an element on the canvas. Final, as no class should extend this
final case class Location (private var _x: Int, private var _y: Int) {

	// Public getters
	def x: Int = _x
	def y: Int = _y

	// Public setters
	def x_=(coordinate: Int): Unit = _x = coordinate
	def y_=(coordinate: Int): Unit = _y = coordinate
}

object Location {
	// public final static fields
	final val DEFAULT_X_LOCATION: Int = 0
	final val DEFAULT_Y_LOCATION: Int = 0
}
