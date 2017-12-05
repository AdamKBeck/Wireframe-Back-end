package wireframe

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * Provides a barricade for altering element values, such as linear property values and
  * location values. Primarily, it makes sure the element is not locked, then checks if the
  * change can fit on the canvas (for example, a location change has to be on the canvas, and
  * the element can't overlap with another element
  *
  * This file contains two classes: WireframeBarricade and WireframeBarricadeLocked.
  * The locked class is a subclass of WireframeBarricade. When an element is locked,
  * its accessor to this file goes to the appropriate barricade class.
  *
  * Sealed, as we only want to inherit if the inheriting template is in the same file as this.
  * As in, WireframeBarricadeLocked can only extend WireframeBarricade as its in the same file
  */
sealed case class WireframeBarricade () {
	/**
	  * Attempts to set an elements width
	  *
	  * @param element the element to have its width set
	  * @param width the width to set for an element, in pixels
	  * @return boolean indicating if the width was set or not
	  */
	def setWidth(element: Element, width: Int): Boolean = {
		// Set the element's width if the new width doesn't overlap or go off the canvas
		if (isValidWidth(element, width)) {
			element.width = width
			true
		}

		// Log an error if the width couldn't be set
		else {
			Logger.instance.log(message = "width could not be set, reason: INVALID WIDTH")
			false
		}
	}

	/**
	  * Attempts to set an elements height
	  *
	  * @param element the element to have its height set
	  * @param height the height to set for an element, in pixels
	  * @return boolean indicating if the height was set or not
	  */
	def setHeight(element: Element, height: Int): Boolean = {
		// Set the element's height if the new height doesn't overlapt of go off the canvas
		if (isValidHeight(element, height)) {
			element.height = height
			true
		}

		// Log an error if the height could not be set
		else {
			Logger.instance.log(message = "height could not be set, reason: INVALID HEIGHT")
			false
		}
	}

	/**
	  * Attempts to set an element's location
	  * @param newX the new x location for an element, in pixels
	  * @param newY the new y location for an element, in pixels
	  * @param element the element to have its location be set
	  * @return boolean indicating if the location was set or not
	  */
	def setLocation(newX: Int, newY:Int, element: Element): Boolean = {
		// If there are no groups that have this element, move it individually
		if (groupWith(element).isEmpty) {
			// Save the old element's positions
			val oldX = element.x
			val oldY = element.y

			// Move the element to the desired location
			element.x = newX
			element.y = newY

			// If the element doesn't overlap and is on the canvas, keep its moved position
			if (isMoveValid(element)) {
				true
			}

			// Revert back to the element's old position if the move could not take place
			else {
				element.x = oldX
				element.y = oldY
				Logger.instance.log(message = "The location of a single element cannot be moved, reason: INVALID LOCATION")
				false
			}
		}

		// If the element is grouped, attempt the move the element and the rest of the group
		else {
			val elementsInGroup = groupWith(element).head.elements

			// Store the relative move difference for "this" element
			val xOffset = newX - element.x
			val yOffset = newY - element.y

			// Move the elements in the group too
			elementsInGroup.map(element => element.x + xOffset)
			elementsInGroup.map(element => element.y + yOffset)

			// If all new locations for grouped elements are valid, keep their moved positions
			if (elementsInGroup.forall(element => isMoveValid(element))) {
				true
			}

			// Revert back to each element's old position if the move could not take place
			else {
				elementsInGroup.map(element => element.x - xOffset)
				elementsInGroup.map(element => element.y - yOffset)
				Logger.instance.log(message = "The location of the elements in this element's group cannot be moved")
				false
			}
		}
	}

	/**
	  * Brings an element to the top of the canvas
	  *
	  * @param element the element to bring to the top of the canvas
	  * @return boolean indicating that the element was brought to the top of the canvas
	  */
	def bringToTop(element: Element): Boolean = {
		element.layerPriority = LinearProperty.TOP
		true
	}

	/**
	  * Brings an element to the bottom of the canvas
	  *
	  * @param element the element to bring to the bottom of the canvas
	  * @return boolean indicating that the element was brought to the bottom of the canvas
	  */
	def bringToBottom(element: Element): Boolean = {
		element.layerPriority = LinearProperty.BOTTOM
		true
	}

	/**
	  * Sets the layer priority of the element
	  *
	  * @param priority the priority in bytes to set the element's layer priority
	  * @param element the element to have its layer priority set
	  * @return boolean indicating that the element had its layer priority set
	  */
	def setLayerPriority(priority: Byte, element: Element): Boolean = {
		element.layerPriority = priority
		true
	}

	/**
	  * Attempts to group an element to a group of elements
	  *
	  * @param element the element to group
	  * @param group a group of elements which an element is trying to group upon
	  * @return a boolean indicating if an element grouped up with the group of elements
	  */
	def group(element: Element, group: Group): Boolean = {
		val groupWithElement = groupWith(element)

		// If another group has the element already, don't group this element up with anything.
		if (groupWithElement.nonEmpty) {
			Logger.instance.log(message = "This element already has a group on the canvas")
			false
		}

		// Group up the element if the element isn't found in another group
		else {
			group.add(element)
			true
		}
	}

	/**
	  * Annotates an element given text
	  *
	  * @param element the element to annotate
	  * @param text the text to annotate the element with
	  * @return a boolean indicating an element was annotated
	  */
	def annotate(element: Element, text: String): Boolean = {
		element.annotate(text)
		true
	}

	/**
	  * Attempts to annotate a group of elements.
	  * Self-use of over-ridable method isUnlocked(): It may be overrided in a subclass
	  * @param group the group to annotate
	  * @param annotation the annotation to annotate a group
	  * @return  a boolean indiciating if the group was annotated or not
	  */
	def annotate(group: Group, annotation: Annotation): Boolean = {
		// Annotate the group if the group is unlocked and all elements in it are unlocked
		if (!group.locked && group.elements.forall(e => isUnlocked(e))) {
			group.annotate(annotation)
			true
		}

		// If the group couldn't be annotated, log an error
		else {
			Logger.instance.log(message = "This group cannot be annotated")
			false
		}
	}

	/**
	  * Checks if a moved elemnet is non-overlapping and in the canvas
	  * @param element the element that was moved
	  * @return a boolean indicated if the move was non-overlapping and in the canvas
	  */
	private def isMoveValid(element: Element): Boolean = {
		// If the element is non-overlapping and in the canvas, it was validly moved
		if (isValidWidth(element, element.width) && isValidHeight(element, element.height)) {
			true
		}

		// if the element was invalidly moved, log an error
		else {
			Logger.instance.log(message = "This element cannot be moved here")
			false
		}
	}

	/**
	  * Checks if an element is able to be altered (unlocked and not part of a locked group)
	  *
	  * @param element the element to check if its able to be altered
	  * @return a boolean indicating if the element was able to be altered
	  */
	def isUnlocked(element: Element): Boolean = {
		// If no locked groups contain the element, the element can be altered
		if (isUnlockedGroupContaining(element)) {
			true
		}

		// If the element is contained in a locked group, log this error
		else {
			Logger.instance.log(message = "This element's group is not unlocked")
			false
		}
	}

	/**
	  *
	  * Checks if an element is part of no locked groups
	  *
	  * @param element the element to check against locked groups
	  * @return a boolean indicating if the element is part no locked groups
	  */
	private def isUnlockedGroupContaining(element: Element): Boolean = {
		// Finds a group (if any) that contains the element in question
		val groupWithElement = groupWith(element)

		// Filters this group out if it's locked
		val unlockedGroupWithElement = groupWithElement.filter(group => group.locked)

		// If all groups that contain the element aren't locked, or the element is standalone, the function is in the correct path
		if (unlockedGroupWithElement.nonEmpty || groupWith(element).size == 0) {
			true
		}

		// If the element was part of a locked group, log this error
		else {
			Logger.instance.log(message = "There is not an unlocked group containing this element")
			false
		}
	}

	/**
	  * Checks if a proposed width on an element is non-overlapping and in the canvas
	  *
	  * @param element the element to check its proposed width
	  * @param width the proposed width of the element, in pixels
	  * @return a boolean indicating if the proposed width on an element is non-overlapping and in the canvas
	  */
	private def isValidWidth(element: Element, width: Int): Boolean = {
		// If the width is in the canvas and doesn't overlap other elements, the element has a valid proposed width
		if (isWidthInCanvas(element, width) && !isWidthOverlapping(element, width)) {
			true
		}
		// If the proposed width isn't valid, log this error
		else {
			Logger.instance.log(message = "This element can't have this width, it's invalid")
			false
		}
	}

	/**
	  * Checks if a proposed width on an element is in the canvas
	  * @param element the element to check its proposed width
	  * @param width the proposed width of the element, in pixels
	  * @return a boolean indicating if the proposed width on an element is in the canvas
	  */
	private def isWidthInCanvas(element: Element, width: Int): Boolean = {
		// The proposed width if valid if it's not out of bounds of the canvas
		if ((element.x + width) <= Canvas.instance.width) {
			true
		}
		// If the proposed width is out of bounds, log this error
		else {
			Logger.instance.log(message = "This element's width doesn't fit in the canvas")
			false
		}
	}

	/**
	  * Checks if a proposed width on an element overlaps other elements
	  *
	  * @param element the element to check its proposed width
	  * @param width the proposed width of the element, in pixels
	  * @return
	  */
	private def isWidthOverlapping(element: Element, width: Int): Boolean = {
		var otherElements = elementsExcept(element)

		// Filter all the elements that aren't to the left of our element
		otherElements = otherElements.filterNot(e => (e.x + e.width) < element.x)

		// Filter all the elements that aren't to the right of our element
		otherElements = otherElements.filterNot(e => e.x > element.x + width)

		// If no elements are not to the left and right of our element, the element isn't overlapping anything
		if (otherElements.nonEmpty) {
			true
		}

		// If the proposed width is overlapping, log this error
		else {
			Logger.instance.log(message = "This element's width is overlapping")
			false
		}
	}

	/**
	  * Checks if a proposed height on an element is non-overlapping and in the canvas
	  * @param element the element to check its proposed height
	  * @param height the proposed height of the element, in pixels
	  * @return a boolean indicating if the proposed height on an element is non-overlapping and in the canvas
	  */
	private def isValidHeight(element: Element, height: Int): Boolean = {
		// If the height is in the canvas and doesn't overlap other elements, the element is a valid proposed height
		if (isHeightInCanvas(element, height) && !isHeightOverlapping(element, height)) {
			true
		}
		// If the proposed height isn't valid, log this error
		else {
			Logger.instance.log(message = "This element's height is invalid")
			false
		}
	}

	/**
	  *
	  * Checks if a proposed height on an element is in the canvas
	  * @param element the element to check its proposed height
	  * @param height the proposed height of the element, in pixels
	  * @return a boolean indicating if the proposed height on an element is in the canvas
	  */
	private def isHeightInCanvas(element: Element, height: Int): Boolean = {
		// The proposed height if valid if it's not out of bounds of the canvas
		if ((element.y + height) <= Canvas.instance.height) {
			true
		}

		// If the proposed width is out of bounds, log this error
		else {
			Logger.instance.log(message = "This element's height doesn't fit in the canvas")
			false
		}
	}

	/**
	  * Checks if a proposed width on an element overlaps other elements
	  *
	  * @param element the element to check its proposed width
	  * @param height the proposed height of the element, in pixels
	  * @return a boolean indicating if the proposed height on an element is overlapping
	  */
	private def isHeightOverlapping(element: Element, height: Int): Boolean = {
		var otherElements = elementsExcept(element)

		// Filter all the elements that aren't above (vertically up) from our element
		otherElements = otherElements.filterNot(e => (e.y + e.height) < element.y)

		// Filter all the elements that aren't below (vertically down) from our element
		otherElements = otherElements.filterNot(e => e.y > element.y +height)

		// If no elements are not to the top and bottom of our element, the element isn't overlapping anything
		if (otherElements.nonEmpty) {
			true
		}

		// If the proposed width is overlapping, log this error
		else {
			Logger.instance.log(message = "This element's height overlaps other elements")
			false
		}
	}

	/* I am not adding the error logger to these, as these are helper functions for more specific functions above.
	 * The real error comes from the specific reason above that the user needs to know about, not these ones down here */

	/**
	  * Helper function to return the group(s) that contains a specified element
	  *
	  * @param element the element to check grouping against
	  * @return a list of groups that the element is in
	  */
	private def groupWith(element: Element): List[Group] = {
		// Find all groups who have at least one element that is equal to this element
		Canvas.instance.groups.filter(group => group.elements.contains(element))
	}

	/**
	  * Helper function to return all the elements on the canvas besides our current one
	  *
	  * @param element the element to check against
	  * @return a list of elements which are all elements besides the passed element
	  */
	private def elementsExcept(element: Element): List[Element] = {
		// Filter out elements not equal to our passed element
		Canvas.instance.elements.filterNot(_ == element)
	}

}

object WireframeBarricade {
	/**
	  * A static factory named constructor.
	  *
	  * For clarity, in case in the future another named constructor is needed.
	  * @return a new instance of WireframeBarricade
	  */
	def instance: WireframeBarricade = WireframeBarricade()
}

final class WireframeBarricadeLocked() extends WireframeBarricade {
	/**
	  * This implementation logs an error as the element is locked and can't have width set
	  *
	  * @param element the element to have its width set
	  * @param width the width to set for an element, in pixels
	  * @return boolean indicating the width was not set
	  */
	override def setWidth(element: Element, width: Int): Boolean = {
		Logger.instance.log(message = "width could not be set, reason: LOCKED")
		false
	}

	/**
	  * This implementation logs an error as the element is locked and can't have its height set
	  *
	  * @param element the element to have its height set
	  * @param height the height to set for an element, in pixels
	  * @return boolean indicating the height was not set
	  */
	override def setHeight(element: Element, height: Int): Boolean = {
		Logger.instance.log(message = "height could not be set, reason: LOCKED")
		false
	}

	/**
	  * This implementation logs an error as the element is locked and can't have its location set
	  *
	  * @param newX the new x location for an element, in pixels
	  * @param newY the new y location for an element, in pixels
	  * @param element the element to have its location be set
	  * @return boolean indicating the location was not set
	  */
	override def setLocation(newX: Int, newY: Int, element: Element): Boolean = {
		Logger.instance.log(message = "location could not be set, reason: LOCKED")
		false
	}

	/**
	  * This implementation logs an error as the element is locked and can't be brought up to top
	  *
	  * @param element the element to bring to the top of the canvas
	  * @return boolean indicating that the element was not brought to the top of the canvas
	  */
	override def bringToTop(element: Element): Boolean = {
		Logger.instance.log(message = "Element could not be brought to top, reason: LOCKED")
		false
	}

	/**
	  * This implementation logs an error as the element is locked and can't be brought to the bottom
	  *
	  * @param element the element to bring to the bottom of the canvas
	  * @return boolean indicating that the element was not brought to the bottom of the canvas
	  */
	override def bringToBottom(element: Element): Boolean = {
		Logger.instance.log(message = "Element could not be brought to bottom, reason: LOCKED")
		false
	}

	/**
	  * This implementation logs an error as the element is locked and can't have its layer priority set
	  *
	  * @param priority the priority in bytes to set the element's layer priority
	  * @param element the element to have its layer priority set
	  * @return boolean indicating that the element did not have its layer priority set
	  */
	override def setLayerPriority(priority: Byte, element: Element): Boolean = {
		Logger.instance.log(message = "The layer priority could not be set for the element, reason: LOCKED")
		false
	}

	/**
	  * This implementation logs an error as the element is locked and can't have grouping done upon it
	  *
	  * @param element the element to group
	  * @param group a group of elements which an element is trying to group upon
	  * @return a boolean indicating the element was not grouped
	  */
	override def group(element: Element, group: Group): Boolean = {
		Logger.instance.log(message = "This element cannot be grouped to this group, reason: LOCKED")
		false
	}

	/**
	  * This implementation logs an error as the element is locked and can't be annotated
	  *
	  * @param element the element to annotate
	  * @param text the text to annotate the element with
	  * @return a boolean indicating an element was not annotated
	  */
	override def annotate(element: Element, text: String): Boolean = {
		Logger.instance.log(message = "This element cannot be annotated, reason: LOCKED")
		false
	}

	/**
	  * This implementation logs an error as the element is locked and can't possibly be unlocked at the moment
	  *
	  * @param element the element to check if its able to be altered
	  * @return a boolean indicating the element is not unlocked at the moment
	  */
	override def isUnlocked(element: Element): Boolean = {
		Logger.instance.log(message = "This element is not unlocked")
		false
	}
}

object WireframeBarricadeLocked {
	/**
	  * A static factory named constructor.
	  *
	  * For clarity, in case in the future another named constructor is needed.
	  * @return a new instance of WireframeBarricadeLocked
	  */
	def instance: WireframeBarricadeLocked = new WireframeBarricadeLocked()
}
