package wireframe

import org.scalatest.FlatSpec

class GroupTest extends FlatSpec {

	// Good data: min normal config
	behavior of "add"
	it should "Test nominally, min normal configuration" in {
		val group = Group()
		group.add(new Slider())
		assert(group.elements.size == 1)
	}

	// Good data: normal/max config
	it should "test normal/max config" in {
		val group = Group()
		group.add(new Slider())
		group.add(new Paragraph())
		group.add(new Slider())
		group.add(new ScrollBar())
		group.add(new ProgressBar())
		assert(group.elements.size == 5)
	}

	// Good data: min normal config
	behavior of "remove"
	it should "Test nominally, min normal config" in {
		val group = Group()
		val element = new Slider()
		group.add(element)
		group.remove(element)
		assert(group.elements.isEmpty)
	}

	// Good data: normal/max config
	it should "Test nominally, normal/max config" in {
		val group = Group()
		val element = new Slider()
		val progress = new ProgressBar()
		val scroll = new ScrollBar()
		group.add(element)
		group.add(progress)
		group.add(scroll)
		group.remove(element)
		group.remove(progress)
		group.remove(scroll)
		assert(group.elements.isEmpty)
	}

	// Bad data: removing an element that isn't there
	it should "Test bad data: removing an element not found" in {
		val group = Group()
		group.add(new Slider())
		group.remove(new ProgressBar())
		assert(group.elements.size == 1)
	}

	// Good data: min normal config
	behavior of "annotate/deannotate"
	it should "test nominally min config" in {
		val group = Group()
		val element = new Slider()
		val annotation = Annotation(_linearProperty = element.linearProperty)
		group.add(element)
		group.annotate(annotation)
		group.deannotate(annotation)
		assert(group.annotations.isEmpty)
	}

	// Good data: normal/max config
	it should "test normal/max config" in {
		val group = Group()
		val element = new Slider()
		val annotation = Annotation(_linearProperty = element.linearProperty)
		group.add(element)
		group.annotate(annotation)
		group.annotate(annotation)
		group.annotate(annotation)
		group.annotate(annotation)
		group.deannotate(annotation)
		group.deannotate(annotation)
		assert(group.annotations.size == 2)
	}

	// Law of demeter testing
	behavior of "annotation visiblity/linearProperty/message"
	it should "test law of demeter functions" in {
		val slider = new Slider()
		val group = Group()
		group.add(slider)

		val annotation = Annotation(_linearProperty =  slider.linearProperty, _message = "test")
		group.annotate(annotation)
		group.annotations.head.visibility = true

		group.setAnnotationVisibility(0, true)
		group.setAnnotationMessage(0, "new text")

		assert(group.annotationMessage(0) == "new text")
		assert(group.annotationLinearProperty(0).x == 0)
		assert(group.annotationVisibility(0))
	}
}
