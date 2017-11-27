/* Provides width, height, and location data to an element. Final, as no class should extend this
 * Singleton, as we only want one
 */
final case class LinearProperty private(private var _width: Int,
	private var _height: Int,
	private val _location: Location,
	private var _layerPriority: Byte) {

	// Public getters
	def width: Int = _width
	def height: Int = _height
	def location: Location = _location
	def layerPriority: Byte = _layerPriority

	// Public setters
	def width_=(value: Int): Unit = _width = value
	def height_=(value: Int): Unit = _height = value
	def layerPriority_=(priority: Byte): Unit = _layerPriority = priority

	// Public functions
	// Brings an element to the top of all other elements
	def bringToTop(): Unit = _layerPriority = LinearProperty.TOP

	// Brings an element to teh bottom of all other elements
	def bringToBottom(): Unit = _layerPriority = LinearProperty.BOTTOM

	// Functions to follow law of demeter: Location getters
	def x: Int = _location.x
	def y: Int = _location.y

	// Functions to follow law of demeter: Location setters
	def x_=(coordinate: Int): Unit = _location.x = coordinate
	def y_=(coordinate: Int): Unit = _location.y = coordinate
}

object LinearProperty {
	// public final static fields
	final val BOTTOM: Byte = -128
	final val TOP: Byte = 127
	final val DEFAULT_WIDTH: Int = 0
	final val DEFAULT_HEIGHT: Int = 0

	final val DEFAULT_LINEAR_PROPERTY: LinearProperty = LinearProperty(DEFAULT_WIDTH,
		DEFAULT_HEIGHT,
		Location(Location.DEFAULT_X_LOCATION, Location.DEFAULT_Y_LOCATION),
		TOP)
}
