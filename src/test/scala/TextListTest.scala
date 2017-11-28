package wireframe

import org.scalatest.FlatSpec

class TextListTest extends FlatSpec {

	// Good data: min config
	behavior of "add"
	it should "test nominally, min config" in {
		val text = new TextList()
		val paragraph = new Paragraph()
		text.add(paragraph)
		assert(text.paragraphs.head == paragraph)
	}

	// Good data: normal/max config
	it should "test nominally, normal/max config" in {
		val text = new TextList()
		val paragraph = new Paragraph()
		text.add(paragraph)
		text.add(paragraph)
		text.add(paragraph)
		paragraph.alignment == "Right"
		text.add(paragraph)
		assert(text.paragraphs.size == 4)
	}

	// Good data: min config
	behavior of "remove"
	it should "test nominally, min config" in {
		val text = new TextList()
		val paragraph = new Paragraph()
		text.add(paragraph)
		text.remove(0)
		assert(text.paragraphs.isEmpty)
	}

	// Good data: normal/max config
	it should "test nominally, normal/max config" in {
		val text = new TextList()
		val paragraph = new Paragraph()
		text.add(paragraph)
		text.add(paragraph)
		text.add(paragraph)
		paragraph.alignment == "Right"
		text.add(paragraph)
		text.remove(3)
		text.remove(0)
		assert(text.paragraphs.size == 2)
	}

	// Bad data: index out of bounds
	it should "Test bad data: out of bounds index" in {
		val text = new TextList()
		assertThrows[IndexOutOfBoundsException](text.remove(0))
	}

	behavior of "final static fields"
	it should "hold the correct TYPE" in {
		assert(TextList.TYPE == "TextList")
	}
}
