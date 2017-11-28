// Provides information and functionalities for Images
package wireframe

import scala.collection.mutable.ListBuffer

/*  Note the protected constructor similar to the Element class
 * 	Final, as no class should extend this
 */
final class Image (private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer())
	extends Box(_linearProperty, _locked, _annotations) {
}

object Image {
	/* This "breaks abstraction badly", but how would a GUI know what it's drawing?
	 * If I am creating a slider, the only way to know what to add to my GUI is to check this field.
	 * Otherwise, all I know is that this is a subclass of an element, its shape and linear properties,
	 * but none of this definitively tells me what type of element I have.
	 *
	 * When my Canvas returns a list of elements, I have all the linear properties. From there, the only
	 * step to draw them on my GUI is to check this field, so I know what to draw (e.g. a JTextBox if
	 * this field says "Box" or a JSlider if this field says "slider"
	 */
	final val TYPE: String = "Image"
}