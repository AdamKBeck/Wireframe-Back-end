package wireframe

import org.scalatest.FlatSpec

class ElementTest extends FlatSpec{
	// Good data: Min normal config
	behavior of "annotate"
	it should "Test min config nominally" in {
		val slider = new Slider()
		slider.annotate("test")

		assert(slider.annotations.head.message == "test")
	}

	// Good data: normal and max config
	it should "Test normal/max config" in {
		val slider = new Slider()
		slider.annotate("test")
		slider.annotate("test")
		slider.annotate("another one")
		slider.annotate("test")
		slider.annotate("more test examples")
		slider.annotate("test")

		assert(slider.annotations.size == 6)
	}


	// Good data: integer and min config
	behavior of "deannotate"
	it should "Test min config nominally" in {
		val slider = new Slider()
		slider.annotate("test")
		slider.deannotate(0)
		assert(slider.annotations.isEmpty)
	}

	// Good ata: integer and normal/max config
	it should "Test normal/max config nominally" in {
		val slider = new Slider()
		slider.annotate("test")
		slider.annotate("test")
		slider.annotate("test")
		slider.annotate("another one")
		slider.annotate("test")
		slider.annotate("more test examples")
		slider.annotate("test")
		slider.deannotate(3)
		slider.deannotate(3)
		slider.deannotate(1)
		slider.deannotate(3)
		assert(slider.annotations.size == 3)
	}

	// Bad data: non-integer
	it should "Test bad data: non integer removal" in {
		val slider = new Slider()
		slider.annotate("this is a test")

		assertThrows[IndexOutOfBoundsException](slider.deannotate(3))
	}

	// Law of demeter test
	behavior of "bringToTop/Bottom"
	it should "Bring an element to the top and bottom" in {
		val slider = new Slider()
		slider.bringToBottom()
		assert(slider.layerPriority == LinearProperty.BOTTOM)
		slider.bringToTop()
		assert(slider.layerPriority == LinearProperty.TOP)

	}

	// more law of demeter testing
	behavior of "annotation visiblity/linearProperty/message"
	it should "Test nominally - getting linearProperty items" in {
		val slider = new Slider()
		slider.annotate("sample text")
		slider.annotations.head.visibility = true

		slider.setAnnotationVisibility(0, true)
		slider.setAnnotationMessage(0, "new text")

		assert(slider.annotationMessage(0) == "new text")
		assert(slider.annotationLinearProperty(0).x == 0)
		assert(slider.annotationVisibility(0) == true)
	}
}
