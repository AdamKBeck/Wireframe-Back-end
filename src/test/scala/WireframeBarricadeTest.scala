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

	// Good data: normal and max config
	it should "test nominally, normal/max config" in {
		clear()
		val canvas = Canvas.instance

		val elementsExcept = PrivateMethod[List[Element]]('elementsExcept)
		val element = new ProgressBar()
		val otherElement = new Slider()
		canvas.add(element)
		canvas.add(otherElement)
		canvas.add(otherElement)
		canvas.add(otherElement)
		canvas.add(otherElement)
		val result = WireframeBarricade.instance.invokePrivate(elementsExcept(element))

		assert(result.size == 4)
	}

	// Bad data: no elements are on the canvas
	it should "test bad data, no elements on the canvas" in {
		clear()

		val canvas = Canvas.instance

		val element = new ProgressBar()
		val elementsExcept = PrivateMethod[List[Element]]('elementsExcept)
		val result = WireframeBarricade.instance.invokePrivate(elementsExcept(element))

		assert(result.isEmpty)
	}

	// Good data: min normal config
	behavior of "groupWith"
	it should "test nominally, min normal config" in {
		clear()

		val canvas = Canvas.instance
		val elementA = new ProgressBar()
		val elementB = new Slider()
		val elementC = new Slider()

		val group = Group()
		group.add(elementA)
		group.add(elementB)
		group.add(elementC)

		canvas.add(group)

		val groupWith = PrivateMethod[List[Group]]('groupWith)
		val result = WireframeBarricade.instance.invokePrivate(groupWith(elementA))
		assert(result.size == 1)
	}

	// Bad data: no groups contain the element
	it should "test bad data, no groups contain the element" in {
		clear()

		val canvas = Canvas.instance
		val elementA = new ProgressBar()
		val elementB = new Slider()
		val elementC = new Slider()

		val group = Group()
		group.add(elementA)
		group.add(elementB)
		group.add(elementC)


		val groupWith = PrivateMethod[List[Group]]('groupWith)
		val result = WireframeBarricade.instance.invokePrivate(groupWith(elementA))
		assert(result.isEmpty)
	}


	// Bad data: normal/max config: element is in multiple groups
	it should "test bad data, multiple groups contain the element" in {
		clear()

		val canvas = Canvas.instance
		val elementA = new ProgressBar()
		val elementB = new Slider()
		val elementC = new Slider()

		val group = Group()
		group.add(elementA)
		group.add(elementB)
		group.add(elementC)

		canvas.add(group)
		canvas.add(group)

		val groupWith = PrivateMethod[List[Group]]('groupWith)
		val result = WireframeBarricade.instance.invokePrivate(groupWith(elementA))
		assert(result.size == 2)
	}

}
