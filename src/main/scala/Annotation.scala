/* Provides information and functionalities for annotations.
 * A single elements or an entire group itself can have an annotation for it.
 * Final, as no class should extend this.
 */
package wireframe

final case class Annotation(private var _visibility: Boolean = false, // "typically invisible"
	private val _linearProperty: LinearProperty, // An element's linear property
	private var _message: String = "") {

	// Public getters
	def visibility: Boolean = _visibility
	def linearProperty: LinearProperty = _linearProperty
	def message: String = _message

	// Public setters
	def visibility_=(value: Boolean): Unit = _visibility = value
	def message_=(newMessage: String): Unit = _message = newMessage
}
