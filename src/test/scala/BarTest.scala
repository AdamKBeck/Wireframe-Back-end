package wireframe

import org.scalatest.FlatSpec

class BarTest extends FlatSpec {
	// Testing the final static fields
	behavior of "Static fields"
	it should "Hold the minimum and maximum bar values" in {
		assert(Bar.MAXIMUM_VALUE == 100 && Bar.MINIMUM_VALUE == 0)
	}

}
