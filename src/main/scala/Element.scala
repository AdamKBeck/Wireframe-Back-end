case class Element(private val _linearProperty: LinearProperty) {

	def linearProperty = _linearProperty

}

object Element {
	def main(args: Array[String]): Unit = {
		val obj = Element(LinearProperty.instance)
		println("Initial values")
		printf("Width: %d\n", obj.linearProperty.width)
		printf("Height: %d\n", obj.linearProperty.height)
		printf("Layer Priority: %d\n", obj.linearProperty.layerPriority)

		println("\n Altered values")
		obj.linearProperty.width = 99
		obj.linearProperty.height = 99
		obj.linearProperty.layerPriority = 23

		printf("Width: %d\n", obj.linearProperty.width)
		printf("Height: %d\n", obj.linearProperty.height)
		printf("Layer Priority: %d\n", obj.linearProperty.layerPriority)

		obj.linearProperty.bringToBottom()
		printf("Layer Priority: %d\n", obj.linearProperty.layerPriority)

		obj.linearProperty.bringToTop()
		printf("Layer Priority: %d\n", obj.linearProperty.layerPriority)
	}
}
