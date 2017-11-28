package wireframe

import org.scalatest.FlatSpec

class CanvasTest extends FlatSpec {
	// Good data: min normal config
	behavior of "add"
	it should "test nominally, min normal config" in {
		val canvas = Canvas.instance
		val element = new Slider()
		canvas.add(element)

		val annotation = Annotation(_linearProperty =  element.linearProperty)
		canvas.add(annotation)
		val group = Group()
		group.add(new RoundedBox())
		canvas.add(group)

		assert(canvas.elements.size >= 1)
		assert(canvas.groups.size >= 1)
		assert(canvas.annotations.size >= 1)
	}

	// Good data: normal and max config
	it should "test nominally, normal and max config" in {
		val canvas = Canvas.instance
		canvas.add(new Slider())
		canvas.add(new Slider())
		canvas.add(new Slider())
		canvas.add(new Slider())
		canvas.add(new ScrollBar())
		canvas.add(new Image())
		canvas.add(new ScrollBar())
		canvas.add(new Paragraph())
		assert(canvas.elements.size >= 8)
	}

	behavior of "remove"
	it should "test nominally, min normal config" in {
		val canvas = Canvas.instance
		canvas.add(new RoundedBox())
		canvas.remove(new RoundedBox())

		val group = Group()
		group.add(new RoundedBox())
		canvas.add(group)

		val element = new RoundedBox()
		val annotation = Annotation(_linearProperty =  element.linearProperty)
		canvas.add(annotation)

		canvas.remove(group)
		canvas.remove(annotation)

		assert(!canvas.elements.forall(e => e == new RoundedBox()))
		assert(canvas.groups.nonEmpty)
		assert(canvas.annotations.nonEmpty)
	}
	it should "test nominally, normal/max config" in {
		val canvas = Canvas.instance
		canvas.add(new RoundedBox())
		canvas.remove(new RoundedBox())
		canvas.add(new RoundedBox())
		canvas.remove(new RoundedBox())
		canvas.add(new RoundedBox())
		canvas.remove(new RoundedBox())
		canvas.add(new RoundedBox())
		canvas.remove(new RoundedBox())
		canvas.add(new RoundedBox())
		canvas.remove(new RoundedBox())
		canvas.add(new RoundedBox())
		canvas.remove(new RoundedBox())
		assert(!canvas.elements.forall(e => e == new RoundedBox()))
	}

	behavior of "static fields"
	it should "hold default dimensions" in {
		assert(Canvas.DEFAULT_HEIGHT == 800  && 800 == Canvas.DEFAULT_WIDTH)
	}



}
