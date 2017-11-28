package wireframe

import org.scalatest.FlatSpec
import org.scalatest.PrivateMethodTester

class WireframeBarricadeTest extends FlatSpec with PrivateMethodTester {

	// Because Canvas is a singleton, we call this before every test
	private def clear() = Canvas.instance.clear

	// Good data: min normal config
	behavior of "elements except"
	it should "test nominally, min normal config" in {
		clear()
		val canvas = Canvas.instance

		val elementsExcept = PrivateMethod[List[Element]]('elementsExcept)
		val element = new ProgressBar()
		canvas.add(element)
		val result = WireframeBarricade.instance.invokePrivate(elementsExcept(element))

		assert(result.isEmpty)
	}


}
