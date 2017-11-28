package wireframe

import org.scalatest.FlatSpec

class LinearPropertyTest extends FlatSpec {
	// Law of demeter test
	behavior of "bringToTop/Bottom"
	it should "Bring an element to the top and bottom" in {
		val slider = new Slider()
		val property = slider.linearProperty
		property.bringToBottom()
		assert(slider.layerPriority == LinearProperty.BOTTOM)
		property.bringToTop()
		assert(slider.layerPriority == LinearProperty.TOP)
	}

	behavior of "static fields"
	it should "return the correct min/max values" in {
		assert(LinearProperty.BOTTOM == -128)
		assert(LinearProperty.TOP == 127)
		assert(LinearProperty.DEFAULT_HEIGHT== 0)
		assert(LinearProperty.DEFAULT_WIDTH== 0)
		assert(LinearProperty.DEFAULT_LINEAR_PROPERTY.x == Location.DEFAULT_X_LOCATION)
		assert(LinearProperty.DEFAULT_LINEAR_PROPERTY.y == Location.DEFAULT_Y_LOCATION)
	}

}
