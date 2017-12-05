package wireframe

import org.scalatest.FlatSpec
import org.scalatest.PrivateMethodTester
import java.util.Random // For stress test

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
		val result = element.barricadeInstance.invokePrivate(elementsExcept(element))

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
		val result = element.barricadeInstance.invokePrivate(elementsExcept(element))

		assert(result.size == 4)
	}

	// Bad data: no elements are on the canvas
	it should "test bad data, no elements on the canvas" in {
		clear()

		val canvas = Canvas.instance

		val element = new ProgressBar()
		val elementsExcept = PrivateMethod[List[Element]]('elementsExcept)
		val result = element.barricadeInstance.invokePrivate(elementsExcept(element))

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
		val result = elementA.barricadeInstance.invokePrivate(groupWith(elementA))
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
		val result = elementA.barricadeInstance.invokePrivate(groupWith(elementA))
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
		val result = elementA.barricadeInstance.invokePrivate(groupWith(elementA))
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
		val result = elementA.barricadeInstance.invokePrivate(isHeightOverlapping(elementA,
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
		val result = elementA.barricadeInstance.invokePrivate(isHeightOverlapping(elementA,
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
		val result = elementA.barricadeInstance.invokePrivate(isHeightOverlapping(elementA,
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
		val result = element.barricadeInstance.invokePrivate(isHeightInCanvas(element,
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
		val result = element.barricadeInstance.invokePrivate(isHeightInCanvas(element,
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
		val result = element.barricadeInstance.invokePrivate(isValidHeight(element,
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
		val result = element.barricadeInstance.invokePrivate(isValidHeight(element,
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
		val result = element.barricadeInstance.invokePrivate(isValidHeight(element,
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
		val result = elementA.barricadeInstance.invokePrivate(isWidthOverlapping(elementA,
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
		val result = elementA.barricadeInstance.invokePrivate(isWidthOverlapping(elementA,
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
		val result = elementA.barricadeInstance.invokePrivate(isWidthOverlapping(elementA,
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
		val result = element.barricadeInstance.invokePrivate(isWidthInCanvas(element,
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
		val result = element.barricadeInstance.invokePrivate(isWidthInCanvas(element,
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
		val result = element.barricadeInstance.invokePrivate(isValidWidth(element,
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
		val result = element.barricadeInstance.invokePrivate(isValidWidth(element,
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
		val result = element.barricadeInstance.invokePrivate(isValidWidth(element,
			element.width))

		assert(!result)
	}

	// Good data: an unlocked group contains an element
	behavior of "isUnlockedGroupContaining"
	it should "test nominally, min normal config" in {
		clear()
		val canvas = Canvas.instance
		val element = new Slider(_value = 3)
		val element2 = new Slider(_value = 4)
		val group = Group()
		group.add(element)
		group.add(element2)

		canvas.add(group)

		val isUnlockedGroupContaining = PrivateMethod[Boolean]('isUnlockedGroupContaining)
		val result = element.barricadeInstance.invokePrivate(isUnlockedGroupContaining(element))

		assert(result)
	}

	// GOod data: normal and max config.
	it should "test nominally, normal and max config" in {
		clear()
		val canvas = Canvas.instance
		val element = new Slider(_value = 3)
		val element2 = new Slider(_value = 4)
		val group = Group()
		group.add(element)
		group.add(element)
		group.add(element)
		group.add(element)
		group.add(element)
		group.add(element)
		group.add(element2)

		canvas.add(group)

		val isUnlockedGroupContaining = PrivateMethod[Boolean]('isUnlockedGroupContaining)
		val result = element.barricadeInstance.invokePrivate(isUnlockedGroupContaining(element))

		assert(result)
	}

	// Bad data: a group is locked containing the element
	it should "test bad data: a group is locked containing the element" in {
		clear()
		val canvas = Canvas.instance
		val element = new Slider(_value = 3)
		val element2 = new Slider(_value = 4)
		val group = Group()
		group.add(element)
		group.add(element2)

		canvas.add(group)
		group.locked = true


		val isUnlockedGroupContaining = PrivateMethod[Boolean]('isUnlockedGroupContaining)
		val result = element.barricadeInstance.invokePrivate(isUnlockedGroupContaining(element))

		assert(!result)
	}

	// Structured basis: all boolean conditions true
	behavior of "isUnlocked"
	it should "test nominally, all boolean conditions true" in {
		clear()

		val canvas = Canvas.instance
		val element = new Slider(_value = 3)

		val isUnlocked = PrivateMethod[Boolean]('isUnlocked)
		val result = element.barricadeInstance.invokePrivate(isUnlocked(element))

		assert(result)
	}

	// Structrued basis: first boolean condition false
	it should "test structured basis, first boolean condition false" in {
		clear()

		val canvas = Canvas.instance
		val element = new Slider(_value = 3)
		element.locked = true

		val isUnlocked = PrivateMethod[Boolean]('isUnlocked)
		val result = element.barricadeInstance.invokePrivate(isUnlocked(element))

		assert(!result)
	}

	// Structrued basis: second boolean condition false
	it should "test structured basis, second boolean condition false" in {
		clear()

		val canvas = Canvas.instance
		val element = new Slider(_value = 3)
		element.locked = true
		val group = Group()
		group.add(element)
		group.locked = true

		val isUnlocked = PrivateMethod[Boolean]('isUnlocked)
		val result = element.barricadeInstance.invokePrivate(isUnlocked(element))

		assert(!result)
	}

	// Structured basis, all boolean conditions true
	behavior of "isMoveValid"
	it should "test nominally, all boolean conditions true" in {
		clear()

		val canvas = Canvas.instance
		val element = new Slider(_value = 3)
		element.x = 3
		element.y = 3
		element.width = 3
		element.height = 3

		val isMoveValid = PrivateMethod[Boolean]('isMoveValid)
		val result = WireframeBarricade.instance.invokePrivate(isMoveValid(element))

		assert(result)
	}

	// Structured basis, first boolean condition false
	it should "first boolean condition false" in {
		clear()

		val canvas = Canvas.instance
		val element = new Slider(_value = 3)
		element.x = 3
		element.y = 3
		element.width = 3
		element.height = 3
		element.locked = true

		val isMoveValid = PrivateMethod[Boolean]('isMoveValid)
		val result = WireframeBarricade.instance.invokePrivate(isMoveValid(element))

		assert(!result)
	}

	// Structured basis, second boolean condition false
	it should "second boolean condition false" in {
		clear()

		val canvas = Canvas.instance
		val element = new Slider(_value = 3)
		element.x = 3
		element.y = 3
		element.width = 30000
		element.height = 3

		val isMoveValid = PrivateMethod[Boolean]('isMoveValid)
		val result = WireframeBarricade.instance.invokePrivate(isMoveValid(element))

		assert(!result)
	}

	// Structured basis, third boolean condition false
	it should "third boolean condition false" in {
		clear()

		val canvas = Canvas.instance
		val element = new Slider(_value = 3)
		element.x = 3
		element.y = 3
		element.width = 3
		element.height = 300000

		val isMoveValid = PrivateMethod[Boolean]('isMoveValid)
		val result = WireframeBarricade.instance.invokePrivate(isMoveValid(element))

		assert(!result)
	}

	// Structured basis, all boolean conditions true
	behavior of "annotate"
	it should "test nominally, all boolean conditons true" in {
		clear()

		val canvas = Canvas.instance
		val element = new Slider(_value = 3)
		val group = Group()
		group.add(element)

		val annotate= PrivateMethod[Boolean]('annotate)
		val annotation = Annotation(_linearProperty = element.linearProperty)
		val result = WireframeBarricade.instance.invokePrivate(annotate(group, annotation))

		assert(result)
	}

	// Bad data: locked group
	// Structured basis, first boolean condition false
	it should "first boolean condition false" in {
		clear()

		val canvas = Canvas.instance
		val element = new Slider(_value = 3)
		val group = Group()
		group.locked = true
		group.add(element)

		val annotate= PrivateMethod[Boolean]('annotate)
		val annotation = Annotation(_linearProperty = element.linearProperty)
		val result = WireframeBarricade.instance.invokePrivate(annotate(group, annotation))

		assert(!result)
	}

	// Structured basis, second boolean condition false
	it should "second boolean condition false" in {
		clear()

		val canvas = Canvas.instance
		val element = new Slider(_value = 3)
		element.locked = true
		val group = Group()
		group.add(element)

		val annotate= PrivateMethod[Boolean]('annotate)
		val annotation = Annotation(_linearProperty = element.linearProperty)
		val result = WireframeBarricade.instance.invokePrivate(annotate(group, annotation))

		assert(!result)
	}


	// Strucuted basis, if statement true
	behavior of "annotate"
	it should "test nominally" in {
		clear()

		val canvas = Canvas.instance
		val element = new Slider(_value = 3)

		val annotate= PrivateMethod[Boolean]('annotate)
		val result = element.barricadeInstance.invokePrivate(annotate(element,"test"))

		assert(result)
	}

	// Bad data: locked element
	// Strucutred basis, if statement false
	it should "if statement false" in {
		clear()

		val canvas = Canvas.instance
		val element = new Slider(_value = 3)
		element.locked = true

		val annotate= PrivateMethod[Boolean]('annotate)
		val result = element.barricadeInstance.invokePrivate(annotate(element,"test"))

		assert(!result)
	}


	// Structured basis, all boolean conditions true
	behavior of "group"
	it should "test nominally" in {
		clear()

		val canvas = Canvas.instance
		val element = new Slider(_value = 3)
		val group1 = Group()
		group1.add(element)

		val group2 = Group()
		group2.add(element)

		canvas.add(group1)
		canvas.add(group2)

		val group = PrivateMethod[Boolean]('group)
		val result = element.barricadeInstance.invokePrivate(group(element, group1))

		assert(!result)
	}

	// Strucutred basis, first boolean condition false
	it should "fist if statment false" in {
		clear()

		val canvas = Canvas.instance
		val element = new Slider(_value = 3)
		val group1 = Group()
		canvas.add(group1)

		val group = PrivateMethod[Boolean]('group)
		val result = element.barricadeInstance.invokePrivate(group(element, group1))

		assert(result)
	}

	// Second if statement false, first boolean condition
	// Bad data: locked element
	it should "second if false, first boolean condition" in {
		clear()

		val canvas = Canvas.instance
		val element = new Slider(_value = 3)
		element.locked = true
		val group1 = Group()
		canvas.add(group1)

		val group = PrivateMethod[Boolean]('group)
		val result = element.barricadeInstance.invokePrivate(group(element, group1))

		assert(!result)
	}

	// Second if statement false, second boolean condition
	// Bad data: locked element element in gruop
	it should "second if false, second boolean condition" in {
		clear()

		val canvas = Canvas.instance
		val element2 = new Slider()
		element2.locked = true
		val element = new Slider(_value = 3)
		val group1 = Group()
		group1.add(element2)
		canvas.add(group1)

		val group = PrivateMethod[Boolean]('group)
		val result = element.barricadeInstance.invokePrivate(group(element, group1))

		assert(!result)
	}

	// Good data: element is unlocked
	// Structured basis: if statement true
	behavior of "setLayerPriority"
	it should "test nominally" in {
		clear()
		val element = new Slider()

		val p: Byte = 3
		val setLayerPriority = PrivateMethod[Boolean]('setLayerPriority)
		val result = element.barricadeInstance.invokePrivate(setLayerPriority(p, element))

		assert(result)
	}

	// Bad data: element is locked
	// Strucutred basis, if satement is false
	it should "if statement is false" in {
		clear()
		val element = new Slider()
		element.locked = true


		val p: Byte = 3
		val setLayerPriority = PrivateMethod[Boolean]('setLayerPriority)
		val result = element.barricadeInstance.invokePrivate(setLayerPriority(p, element))

		assert(result)
	}

	// Good data: element is unlocked
	// Structured basis: if statement true
	behavior of "bringToBottom"
	it should "test nominally" in {
		clear()
		val element = new Slider()

		val bringToBottom = PrivateMethod[Boolean]('bringToBottom)
		val result = element.barricadeInstance.invokePrivate(bringToBottom(element))

		assert(result)
	}

	// Bad data: element is locked
	// Strucutred basis: if statement is false
	it should "if statement is false" in {
		clear()
		val element = new Slider()
		element.locked = true

		val bringToBottom = PrivateMethod[Boolean]('bringToBottom)
		val result = element.barricadeInstance.invokePrivate(bringToBottom(element))

		assert(!result)
	}

	// Good data: element is unlocked
	// Structured basis: if statement true
	behavior of "bringToTop"
	it should "test nominally" in {
		clear()
		val element = new Slider()

		val bringToTop = PrivateMethod[Boolean]('bringToTop)
		val result = element.barricadeInstance.invokePrivate(bringToTop(element))

		assert(result)
	}

	// Bad data: element is locked
	// Strucutred basis: if statement is false
	it should "if statement is false" in {
		clear()
		val element = new Slider()
		element.locked = true

		val bringToTop = PrivateMethod[Boolean]('bringToTop)
		val result = element.barricadeInstance.invokePrivate(bringToTop(element))

		assert(!result)
	}


	// Good data: moving to a good location
	// Structured basis: all if statements true
	behavior of "setLocation"
	it should "test nominally" in {
		clear()
		val element = new Slider()

		val setLocation = PrivateMethod[Boolean]('setLocation)
		val result = element.barricadeInstance.invokePrivate(setLocation(10, 10, element))

		assert(result)
	}

	// Structured basis: first if statement false
	it should "first if statement false" in {
		clear()
		val element = new Slider()
		element.x = 10
		element.y = 10
		val element2 = new ProgressBar()
		element2.x = 200
		element2.y = 200

		val group = Group()
		group.add(element)
		group.add(element2)

		val setLocation = PrivateMethod[Boolean]('setLocation)
		val result = element.barricadeInstance.invokePrivate(setLocation(11, 10, element))

		assert(result)
	}

	// Bad data: element is locked
	// Structured basis: first if true, inner if false
	it should "first if statement true, inner false" in {
		clear()
		val element = new Slider()
		element.locked = true

		val setLocation = PrivateMethod[Boolean]('setLocation)
		val result = element.barricadeInstance.invokePrivate(setLocation(10, 10, element))

		assert(!result)
	}

	// Bad data: elements in group is locked
	// Structured basis: first if false, inner if false
	it should "first if statement false, inner false" in {
		clear()
		val element = new Slider()
		element.x = 10
		element.y = 10
		val element2 = new ProgressBar()
		element2.x = 200
		element2.y = 200

		val group = Group()
		group.add(element)
		group.add(element2)
		group.locked = true
		element.locked = true

		val setLocation = PrivateMethod[Boolean]('setLocation)
		val result = element.barricadeInstance.invokePrivate(setLocation(11, 10, element))

		assert(!result)
	}

	// Strucutred basis, all conditions true
	// Good data: height is valid
	behavior of "setHeight"
	it should "test nominally, all conditions true" in {
		clear()
		val element = new Slider()

		val setHeight = PrivateMethod[Boolean]('setHeight)
		val result = element.barricadeInstance.invokePrivate(setHeight(element, 10))

		assert(result)
	}

	// Strucutred basis: if statement is false (first boolean condition false)
	// Bad data: element is locked
	it should "bad data, element is locked. first if false" in {
		clear()
		val element = new Slider()
		element.locked = true

		val setHeight = PrivateMethod[Boolean]('setHeight)
		val result = element.barricadeInstance.invokePrivate(setHeight(element, 10))

		assert(!result)
	}

	// Strucutred basis, second boolean condition false
	// Bad data: height too big
	it should "bad data, height too big" in {
		clear()
		val element = new Slider()

		val setHeight = PrivateMethod[Boolean]('setHeight)
		val result = element.barricadeInstance.invokePrivate(setHeight(element, 10000))

		assert(!result)
	}

	// Structured basis, all conditions true
	// Good data: width is valid
	behavior of "setWidth"
	it should "test nominally, all conditions true" in {
		clear()
		val element = new Slider()

		val setWidth = PrivateMethod[Boolean]('setWidth)
		val result = element.barricadeInstance.invokePrivate(setWidth(element, 10))

		assert(result)
	}

	// Strucutred basis: if statement is false (first boolean condition false)
	// Bad data: element is locked
	it should "bad data, element is locked. first if false" in {
		clear()
		val element = new Slider()
		element.locked = true

		val setWidth = PrivateMethod[Boolean]('setWidth)
		val result = element.barricadeInstance.invokePrivate(setWidth(element, 10))

		assert(!result)
	}

	// Strucutred basis, second boolean condition false
	// Bad data: width too big
	it should "bad data, width too big" in {
		clear()
		val element = new Slider()

		val setWidth = PrivateMethod[Boolean]('setWidth)
		val result = element.barricadeInstance.invokePrivate(setWidth(element, 10000))

		assert(!result)
	}

	// Stress test
	// I will generate a bunch of overlapping elements, and show that they collide
	// NOTE: the other tests will randomly fail due to race conditions with
	// the singletons, but this passes since its the only one being slept
	behavior of "overlapping stress test"
	it should "always detect collisions" in {
		Thread.sleep(500)
		clear()

		val canvas = Canvas.instance

		val rand = new Random()
		// Loop 100 times
		for (i <- 1 to 100) {
			// Insert 11 elements where we know for a fact one overlaps the rest
			val element = new RoundedBox()
			val xPos = rand.nextInt(600)
			val yPos = rand.nextInt(600)
			val w = 50
			val h = 50

			element.x = xPos
			element.y = yPos
			element.width = w
			element.height = h

			canvas.add(element)

			for (i <- 1 to 10) {
				val temp = new RoundedBox()
				temp.x = xPos + ( + rand.nextInt(100) % w) // Guaranteed a collision
				temp.y = rand.nextInt(100)
				temp.width = rand.nextInt(100)
				temp.height = rand.nextInt(100)

				canvas.add(temp)
			}
			val barricade = WireframeBarricade.instance

			assert(!canvas.elements.forall(element =>
				barricade.setLocation(element.x, element.y, element)))

			clear()
		}
	}


	// Stress test
	// NOTE: I will show that I can always move non-colliding groups inside the canvas
	// again, this test passes, since I slept it to prevent race conditions with the singletons
	behavior of "moving groups"
	it should "always move a simple non-colliding groups together" in {
		Thread.sleep(1000)
		clear()

		val canvas = Canvas.instance

		val rand = new Random()

		for (i <- 1 to 100) {
			val elementA = new RoundedBox()
			val elementB = new Image()
			val elementC = new ScrollBar()

			// Set A, B, and C close enough so I know the bounds to move them by
			elementA.x = 100
			elementB.x = 200
			elementC.x = 300

			elementA.y = 0
			elementB.y = 100
			elementC.y = 200

			elementA.height= 50
			elementB.height= 50
			elementC.height= 50

			elementA.width = 50
			elementB.width = 50
			elementC.width = 50

			//Diagrammatically, they look like this:
			/*   AA              *
			 *   AA              *
			 *                   *
			 *       BB          *
			 *       BB          *
			 *                   *
			 *          CC       *
			 *          CC       *
			 *                   *
			 *                   *
			 *                   *
			 */


			/* I can deduce that I can move this group up to 450 wide
			 * since my default width is 800, 550 down, 100 left, and 0 up
			 */

			val right = rand.nextInt(440)
			val left = rand.nextInt(90)
			val down = rand.nextInt(540)

			elementA.x += right
			elementB.x += right
			elementC.x += right

			elementA.y += down
			elementB.y += down
			elementC.y += down

			elementA.x -=  left
			elementB.x -=  left
			elementC.x -=  left

			val group = Group()
			group.add(elementA)
			group.add(elementB)
			group.add(elementC)

			canvas.add(group)

			val barricade = WireframeBarricade.instance
			// Now we make sure we can place this on the canvas
			assert(canvas.elements.forall(element =>
				barricade.setLocation(elementA.x, elementA.y, elementA)))
		}

	}


	// Edge cases
	behavior of "edge cases for valid dimensions"
	it should "return true" in {
		val element = new ScrollBar()
		element.locked = false

		val canvas = Canvas.instance

		canvas.add(element)

		val barricade = WireframeBarricade.instance

		assert(barricade.setWidth(element, 10))
	}

	it should "find another group containing this element" in {

		val element = new ScrollBar()
		element.locked = false

		val canvas = Canvas.instance

		canvas.add(element)
		val group = Group()
		group.add(element)

		val group2 = Group()
		group2.add(element)
		canvas.add(group)
		canvas.add(group2)

		val barricade = WireframeBarricade.instance
		assert(barricade.setLocation(10, 10, element))
	}


	it should "catch an element trying to move when locked" in {

		val element = new ScrollBar()
		element.locked = false

		val canvas = Canvas.instance

		canvas.add(element)
		val group = Group()
		group.add(element)
		element.locked = true

		val group2 = Group()
		group2.add(element)
		canvas.add(group)
		canvas.add(group2)

		val barricade = WireframeBarricade.instance
		assert(!barricade.setLocation(10, 10, element))
	}
}
