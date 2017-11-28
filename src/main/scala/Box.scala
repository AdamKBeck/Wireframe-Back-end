/* Provides information and functionalities for Boxes. A RoundedBox and Image are both boxes
 * in the eyes of a wireframe */

package wireframe

import scala.collection.mutable.ListBuffer

class Box(private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer()) extends Element {

}

object Box {
	def main(args: Array[String]): Unit = {
		val a = new Box(_locked = true)
		println(a.x)
		println(a.locked)

	}
}
