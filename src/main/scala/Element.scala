case class Element(private val _linearProperty: LinearProperty = LinearProperty.instance,
	private var _locked: Boolean = false,
	private val _group: Group = Group.instance,
	private val _annotations: List[Annotation] = Nil) {

	// Public getters
	def linearProperty: LinearProperty = _linearProperty
	def locked: Boolean = _locked
	def group: Group = _group
	def annotations: List[Annotation] = _annotations

	// Public setters
	def locked_=(value: Boolean): Unit = _locked = value

	//TODO: methods to follow law of demeter
}

object Element {
	def main(args: Array[String]): Unit = {
		val obj = Element()
		println("Initial values")
		println(obj.linearProperty.location.y)
		obj.linearProperty.location.y = 33
		println(obj.linearProperty.location.y)

		println(obj.linearProperty.width)

	}
}
