// Provides width, height, and location data to an element. Final, as no class should extend this
final case class LinearProperty private(private var _width: Int,
	private var _height: Int,
	private var _location: Location,
	private var _layerPriority: Byte) {

	// Public getters
	def width: Int = _width
	def height: Int = _height
	def location: Location = _location
	def layerPriority: Byte = _layerPriority

	// Public setters
	def width_=(value: Int): Unit = _width = value
	def height_=(value: Int): Unit = _height = value
	def location_=(loc: Location): Unit = _location = loc
	def layerPriority_=(priority: Byte): Unit = _layerPriority = priority

	// Public functions
	def bringToTop(): Unit = _layerPriority = LinearProperty.TOP

	def bringToBottom(): Unit = _layerPriority = LinearProperty.BOTTOM

}

object LinearProperty {
	// public final static fields
	final val BOTTOM: Byte = -128
	final val TOP: Byte = 127
	final val DEFAULT_WIDTH: Int = 0
	final val DEFAULT_HEIGHT: Int = 0

	// Singleton pattern
	private val _instance = LinearProperty(DEFAULT_WIDTH, DEFAULT_HEIGHT,
		Location.instance, TOP)
	def instance: LinearProperty = _instance
}
