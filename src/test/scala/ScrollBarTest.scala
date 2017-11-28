package wireframe

import org.scalatest.FlatSpec

class ScrollBarTest extends FlatSpec {
	behavior of "final static fields"
	it should "return the correct type for the subclass" in {
		assert(ScrollBar.TYPE == "ScrollBar")
	}

}
