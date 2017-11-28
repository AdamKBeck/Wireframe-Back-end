package wireframe

import org.scalatest.FlatSpec

class BarTest extends FlatSpec {
	// Testing the final static fields
	behavior of "Static fields"
	it should "Hold the minimum and maximum bar values" in {
		val bar = new ProgressBar()
		bar.value = 0
		assert(bar.value == 0)
		assert(bar.location.x == 0)
		assert(Bar.MAXIMUM_VALUE == 100 && Bar.MINIMUM_VALUE == 0)
	}

}
