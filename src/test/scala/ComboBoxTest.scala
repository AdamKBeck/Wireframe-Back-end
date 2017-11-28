package wireframe

import org.scalatest.FlatSpec

class ComboBoxTest extends FlatSpec {

	// min normal config
	behavior of "add"
	it should "Test nominally, min normal config" in {
		val box = new ComboBox()
		box.add("example")
		assert(box.choices.size == 1)
	}

	// normal and max config
	it should "Test nominally, normal and max config" in {
		val box = new ComboBox()
		box.add("example")
		box.add("example")
		box.add("another one")
		box.add("example again")
		box.add("test ")
		assert(box.choices.size == 5)
	}


	// min normal config
	behavior of "remove"
	it should "Test nominally, min normal config" in {
		val box = new ComboBox()
		box.add("example")
		box.remove(0)
		assert(box.choices.size == 0)
	}

	// normal and max config
	it should "Test nominally, normal and max config" in {
		val box = new ComboBox()
		box.add("example")
		box.add("example")
		box.add("example")
		box.add("example")
		box.add("example")
		box.remove(1)
		box.remove(3)
		box.remove(0)
		box.remove(0)
		box.remove(0)
		assert(box.choices.size == 0)
	}

	// Bad data: invalid index
	it should "Test with bad data: out of bounds index" in {
		val box = new ComboBox()
		assertThrows[IndexOutOfBoundsException](box.remove(0))
	}
}
