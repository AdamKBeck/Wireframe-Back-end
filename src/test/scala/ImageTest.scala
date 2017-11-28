package wireframe

import org.scalatest.FlatSpec

class ImageTest extends FlatSpec {
		behavior of "type"
		it should "return the correct Element type" in {
			assert(Image.TYPE == "Image")
		}
}
