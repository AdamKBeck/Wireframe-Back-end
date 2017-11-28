package wireframe

import org.scalatest.FlatSpec

class ParagraphTest extends FlatSpec {
	behavior of "final static fields"
	it should "return the correct type for the subclass" in {
		assert(Paragraph.TYPE == "Paragraph")
	}

}
