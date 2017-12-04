/* Provides information and functionalities for Boxes. A RoundedBox and Image are both boxes
 * in the eyes of a wireframe */

package wireframe

import scala.collection.mutable.ListBuffer

// Note the protected constructor similar to the Element class
abstract class Box(private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer())
	extends Element(_linearProperty, _locked, _annotations) {
}

object Box {

}
