// Provides location information of an element on the canvas. Final, as no class should extend this
final case class Location private(private var _x: Int = Location.DEFAULT_X_LOCATION,
	private var _y: Int = Location.DEFAULT_Y_LOCATION) {

	// Public getters
	def x: Int = _x
	def y: Int = _y

	// Public setters
	def x_=(coordinate: Int): Unit = {
		x = coordinate
	}
	def y_=(coordinate: Int): Unit = {
		y = coordinate
	}
}

object Location {
	// final static fields
	final val DEFAULT_X_LOCATION = 0
	final val DEFAULT_Y_LOCATION = 0

	// Singleton pattern
	private val _instance = Location()
	def instance: Location = _instance
}
