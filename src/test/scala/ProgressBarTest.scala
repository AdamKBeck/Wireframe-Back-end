package wireframe

import org.scalatest.FlatSpec

class ProgressBarTest extends FlatSpec {
	behavior of "final static fields"
	it should "return the correct type for the subclass" in {
		assert(ProgressBar.TYPE == "ProgressBar")
	}

}
