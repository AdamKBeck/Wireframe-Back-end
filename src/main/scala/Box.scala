package wireframe

import scala.collection.mutable.ListBuffer

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * Box: A type of Element that has a boxed shape.
  * Abstract, as only sublcasses should have objects being created from them. Box doesn't exist on its own.
  *
  * @param _linearProperty the linear properties that the box follows
  * @param _locked // the status indicating if the box is locked
  * @param _annotations // the list of annotations relating to the box
  */
abstract class Box(private var _linearProperty: LinearProperty = LinearProperty.DEFAULT_LINEAR_PROPERTY,
	private var _locked: Boolean = false,
	private val _annotations: ListBuffer[Annotation] = ListBuffer())
	extends Element(_linearProperty, _locked, _annotations) {
}

object Box {

}
