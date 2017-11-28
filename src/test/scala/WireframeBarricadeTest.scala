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


	// Good data: element is overlapping an element, min normal config
	behavior of "isHeightOverlapping"
	it should "test nominal, min normal config" in {
		clear()

		val elementA = new Slider()
		val elementB = new Slider()
		elementA.height = 20
		elementB.height = 20

		val canvas = Canvas.instance
		canvas.add(elementA)
		canvas.add(elementB)

		val isHeightOverlapping = PrivateMethod[Boolean]('isHeightOverlapping)
		val result = WireframeBarricade.instance.invokePrivate(isHeightOverlapping(elementA,
			elementA.height))

		assert(result)
	}


	// Good data: normal/max config, element is overlapping a few elements
	it should "test nominal, normal/max config" in {
		clear()

		val elementA = new Slider()
		val elementB = new Slider()
		val elementC = new Slider()
		elementA.height = 20
		elementB.height = 20
		elementC.height = 20

		val canvas = Canvas.instance
		canvas.add(elementA)
		canvas.add(elementB)
		canvas.add(elementC)

		val isHeightOverlapping = PrivateMethod[Boolean]('isHeightOverlapping)
		val result = WireframeBarricade.instance.invokePrivate(isHeightOverlapping(elementA,
			elementA.height))

		assert(result)
	}

	// Bad data: element not overlapping anything
	it should "bad data, element not overlapping anything" in {
		clear()

		val elementA = new Slider()
		val elementB = new Slider()
		val elementC = new Slider()
		elementA.height = 20
		elementB.height = 20
		elementC.height = 20
		elementA.x = 0
		elementB.x = 40
		elementC.x = 40

		val canvas = Canvas.instance
		canvas.add(elementA)
		canvas.add(elementB)
		canvas.add(elementC)

		val isHeightOverlapping = PrivateMethod[Boolean]('isHeightOverlapping)
		val result = WireframeBarricade.instance.invokePrivate(isHeightOverlapping(elementA,
			elementA.height))

		assert(!result)
	}

	// Good data: height is in canvas
	behavior of "isHeightInCanvas"
	it should "test nominally" in {
		clear()
		val element = new Slider()
		element.x = 20
		element.y = 20

		val isHeightInCanvas = PrivateMethod[Boolean]('isHeightInCanvas)
		val result = WireframeBarricade.instance.invokePrivate(isHeightInCanvas(element,
			element.height))

		assert(result)
	}

	// Bad data: height not in canvas
	it should "test bad data: height out of canvas bounds" in {
		clear()
		val element = new Slider()
		element.x = 20
		element.y = 1600

		val isHeightInCanvas = PrivateMethod[Boolean]('isHeightInCanvas)
		val result = WireframeBarricade.instance.invokePrivate(isHeightInCanvas(element,
			element.height))

		assert(!result)
	}

	// Structured basis, all boolean conditions true
	// Bad data: element not even on the canvas
	behavior of "isValidHeight"
	it should "test nominally, all boolean conditions true" in {
		clear()
		val element = new Slider()
		element.x = 20
		element.y = 23

		val isValidHeight = PrivateMethod[Boolean]('isValidHeight)
		val result = WireframeBarricade.instance.invokePrivate(isValidHeight(element,
			element.height))

		assert(result)
	}

	// Structured basis, first condition false
	it should "first condition false" in {
		clear()
		val element = new Slider()
		element.x = 30
		element.y = 1600

		val isValidHeight = PrivateMethod[Boolean]('isValidHeight)
		val result = WireframeBarricade.instance.invokePrivate(isValidHeight(element,
			element.height))

		assert(!result)
	}

	// Structured basis, second second condition false
	it should "second condition false" in {
		clear()
		val element = new Slider()
		element.x = 30
		element.y = 1600
		val element2 = new Slider()
		element2.x = 30
		element2.y = 1600
		Canvas.instance.add(element)
		Canvas.instance.add(element2)

		val isValidHeight = PrivateMethod[Boolean]('isValidHeight)
		val result = WireframeBarricade.instance.invokePrivate(isValidHeight(element,
			element.height))

		assert(!result)
	}


	// Good data: element is overlapping an element, min normal config
	behavior of "isWidthOverlapping"
	it should "test nominal, min normal config" in {
		clear()

		val elementA = new Slider()
		val elementB = new Slider()
		elementA.height = 20
		elementB.height = 20

		val canvas = Canvas.instance
		canvas.add(elementA)
		canvas.add(elementB)

		val isWidthOverlapping = PrivateMethod[Boolean]('isWidthOverlapping)
		val result = WireframeBarricade.instance.invokePrivate(isWidthOverlapping(elementA,
			elementA.width))

		assert(result)
	}


	// Good data: normal/max config, element is overlapping a few elements
	it should "test nominal, normal/max config" in {
		clear()

		val elementA = new Slider()
		val elementB = new Slider()
		val elementC = new Slider()
		elementA.height = 20
		elementB.height = 20
		elementC.height = 20

		val canvas = Canvas.instance
		canvas.add(elementA)
		canvas.add(elementB)
		canvas.add(elementC)

		val isWidthOverlapping = PrivateMethod[Boolean]('isWidthOverlapping)
		val result = WireframeBarricade.instance.invokePrivate(isWidthOverlapping(elementA,
			elementA.width))

		assert(result)
	}

	// Bad data: element not overlapping anything
	it should "bad data, element not overlapping anything" in {
		clear()

		val elementA = new Slider()
		val elementB = new Slider()
		val elementC = new Slider()
		elementA.height = 20
		elementB.height = 20
		elementC.height = 20
		elementA.x = 0
		elementB.x = 40
		elementC.x = 40

		val canvas = Canvas.instance
		canvas.add(elementA)
		canvas.add(elementB)
		canvas.add(elementC)

		val isWidthOverlapping = PrivateMethod[Boolean]('isWidthOverlapping)
		val result = WireframeBarricade.instance.invokePrivate(isWidthOverlapping(elementA,
			elementA.width))

		assert(result)
	}

	// Good data: width is in canvas
	behavior of "isWidthInCanvas"
	it should "test nominally" in {
		clear()
		val element = new Slider()
		element.x = 20
		element.y = 20

		val isWidthInCanvas = PrivateMethod[Boolean]('isWidthInCanvas)
		val result = WireframeBarricade.instance.invokePrivate(isWidthInCanvas(element,
			element.width))

		assert(result)
	}

	// Bad data: width not in canvas
	it should "test bad data: width out of canvas bounds" in {
		clear()
		val element = new Slider()
		element.x = 20000
		element.y = 20

		val isWidthInCanvas = PrivateMethod[Boolean]('isWidthInCanvas)
		val result = WireframeBarricade.instance.invokePrivate(isWidthInCanvas(element,
			element.width))

		assert(!result)
	}

	// Structured basis, all boolean conditions true
	// Bad data: element not even on the canvas
	behavior of "isValidWidth"
	it should "test nominally, all boolean conditions true" in {
		clear()
		val element = new Slider()
		element.x = 20
		element.y = 23

		val isValidWidth = PrivateMethod[Boolean]('isValidWidth)
		val result = WireframeBarricade.instance.invokePrivate(isValidWidth(element,
			element.width))

		assert(result)
	}

	// Structured basis, first condition false
	it should "first condition false" in {
		clear()
		val element = new Slider()
		element.x = 30000
		element.y = 20

		val isValidWidth = PrivateMethod[Boolean]('isValidWidth)
		val result = WireframeBarricade.instance.invokePrivate(isValidWidth(element,
			element.width))

		assert(!result)
	}

	// Structured basis, second second condition false
	it should "second condition false" in {
		clear()
		val element = new Slider()
		element.x = 30
		element.y = 1600
		val element2 = new Slider()
		element2.x = 30
		element2.y = 1600
		Canvas.instance.add(element)
		Canvas.instance.add(element2)

		val isValidWidth = PrivateMethod[Boolean]('isValidWidth)
		val result = WireframeBarricade.instance.invokePrivate(isValidWidth(element,
			element.width))

		assert(!result)
	}

}
