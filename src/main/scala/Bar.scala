/* Provides information and functionalities for Bars */

package wireframe

import scala.collection.mutable.ListBuffer

// Note the protected constructor similar to the Element class
abstract class Bar(private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer(),
	private var _value: Int = Bar.MINIMUM_VALUE)
	extends Element(_linearProperty, _locked, _annotations) {

	// public getters
	def value: Int = _value

	// public setters
	def value_=(percentage: Int): Unit = _value = percentage

}

object Bar {
	final val MINIMUM_VALUE = 0
	final val MAXIMUM_VALUE = 100
}
