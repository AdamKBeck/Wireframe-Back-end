package wireframe

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * Lockable: Trait to ensure that users of this trait must implement locking
  */
trait Lockable {
	/**
	  * Gets the locked state of an object using this trait
	  *
	  * @return a boolean representing if an object using this trait is locked
	  */
	def locked: Boolean

	/**
	  * Sets the locked state of an object using this trait
	  *
	  * @param value the locked value, represented as a boolean, to set for an object using this trait
	  */
	def locked_=(value: Boolean): Unit
}
