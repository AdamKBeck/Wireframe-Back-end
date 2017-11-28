package wireframe

import org.scalatest.FlatSpec

class TextTest extends FlatSpec {

	// Good data: in the text cache
	behavior of "test"
	it should "Nominal, return the correct text from the cache" in {
		val paragraph = new Paragraph()
		assert(paragraph.text == Some("BLOCKED...."))
	}

	// Bad data: not in the text cache
	it should "Bad data, test with something not in the text cache" in {
		val textList = new TextList(_textType = "stuff")
		textList.alignment = "anything"
		assert(textList.text == None)
	}

	behavior of "final static fields"
	it should "hold the correct values" in {
		assert(Text.ALIGNMENTS.contains("Left"))
		assert(Text.ALIGNMENTS.contains("Center"))
		assert(Text.ALIGNMENTS.contains("Right"))
		assert(Text.ALIGNMENTS.contains("Justified"))

		assert(Text.textCache("Canned") == "Lorem Ipsum....")
		assert(Text.textCache("Blocked") == "BLOCKED....")
	}

}
