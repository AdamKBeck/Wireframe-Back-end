/* Provides a barricade for altering element values, such as linear property values and
 * location values. Primarily, it makes sure the element is not locked, then checks if the
 * change can fit on the canvas (for example, a location change has to be on the canvas, and
 * the element can't overlap with another element
 *
 * Singleton, as we only want one access to this class.
 * Final, as nothing should extend this
 */

package wireframe

final case class WireframeBarricade private() {
	// TODO: log stuff when false

	// Attempts to change an element's width
	def setWidth(element: Element, width: Int): Boolean = {
		if (isUnlocked(element) && isValidWidth(element, width)) {
			element.width = width
			true
		}
		else {
			false
		}
	}

	// Attempts to change an element's height
	def setHeight(element: Element, height: Int): Boolean = {
		if (isUnlocked(element) && isValidHeight(element, height)) {
			element.height = height
			true
		}
		else {
			false
		}

	}

	// Attempts to change an element's position. This also changes the group too
	def setLocation(newX: Int, newY:Int, element: Element): Boolean = {
		// If there are no groups that have this element, move it individually
		if (groupWith(element).isEmpty) {
			// Save the old element's positions
			val oldX = element.x
			val oldY = element.y

			// Move the element to the desired location
			element.x = newX
			element.y = newY

			// Checks if the new location is valid. If it isn't, revert back to the old location
			if (isMoveValid(element)) {
				true
			}

			else {
				element.x = oldX
				element.y = oldY
				false
			}
		}

		// Attempt the move the element and the rest of the group
		else {
			val elementsInGroup = groupWith(element).head.elements

			// Store the relative move difference for "this" element
			val xOffset = newX - element.x
			val yOffset = newY - element.y

			// Move the elements in the group too
			elementsInGroup.map(element => element.x + xOffset)
			elementsInGroup.map(element => element.y + yOffset)

			// Checks if the new location is valid. If it isn't, rever back to the old locations
			if (elementsInGroup.forall(element => isMoveValid(element))) {
				true
			}

			else {
				elementsInGroup.map(element => element.x - xOffset)
				elementsInGroup.map(element => element.y - yOffset)

				false
			}
		}
	}

	// Attempts to bring an element to the top
	def bringToTop(element: Element): Boolean = {
		if (isUnlocked(element)) {
			element.layerPriority = LinearProperty.TOP
			true
		}

		else {
			false
		}
	}

	// Attempts to bring an element to the bottom
	def bringToBottom(element: Element): Boolean = {
		if (isUnlocked(element)) {
			element.layerPriority = LinearProperty.BOTTOM
			true
		}

		else {
			false
		}
	}

	// Attempts to set the layer priority of an element
	def setLayerPriority(priority: Byte, element: Element): Boolean = {
		if (isUnlocked(element)) {
			element.layerPriority = priority
			true
		}
		else {
			false
		}
	}

	// Attempts to add an element to a group. Can't happen if it's in another group, or the group is locked
	def group(element: Element, group: Group): Boolean = {
		val groupWithElement = groupWith(element)

		// If another group has the element already
		if (groupWithElement.nonEmpty) {
			false
		}

		// Otherwise, make sure the element is not locked and neither is the group
		else {
			if (isUnlocked(element) && groupWithElement.head.elements.forall(e => isUnlocked(e)) && !group.locked) {
				group.add(element)
				true
			}

			else {
				false
			}
		}
	}

	// Attempts to add an annotation to an element. Can't happen if the element is locked
	def annotate(element: Element, text: String): Boolean = {
		if (isUnlocked(element)){
			element.annotate(text)
			true
		}
		else {
			false
		}
	}

	// Attempts to add an annotation to a group. Can't happen if the group is locked or group elements are too.
	def annotate(group: Group, annotation: Annotation): Boolean = {
		if (!group.locked && group.elements.forall(e => isUnlocked(e))) {
			group.annotate(annotation)
			true
		}

		else {
			false
		}
	}

	// Checks if an element was moved to a valid spot (in the canvas, non overlapping)
	private def isMoveValid(element: Element): Boolean = {
		isUnlocked(element) && isValidWidth(element, element.width) && isValidHeight(element, element.height)
	}

	// Returns based on if an element is unlocked and the group its in is unlocked
	private def isUnlocked(element: Element): Boolean = {
		!element.locked && isUnlockedGroupContaining(element)
	}

	/* Returns based on if a group containing an element is unlocked.
	 * Works by finding the group (if any) that contain the element, then filters it out if
	 * the group is locked. Then if we have a group that's unlocked, it meets the condition of this function call
	 */
	private def isUnlockedGroupContaining(element: Element): Boolean = {
		// Finds a group (if any) that contains the element in question
		val groupWithElement = groupWith(element)

		// Filters this group out if it's locked
		val unlockedGroupWithElement = groupWithElement.filter(group => !group.locked)

		// Therefore, we are left with a group that contains our element and the group is unlocked
		unlockedGroupWithElement.nonEmpty
	}

	// Checks if a proposed width is in the canvas and doesn't overlap anything
	private def isValidWidth(element: Element, width: Int): Boolean = {
		isWidthInCanvas(element, width) && isWidthOverlapping(element, width)
	}

	// Checks if a proposed width is within the canvas
	private def isWidthInCanvas(element: Element, width: Int): Boolean = {
		(element.x + width) <= Canvas.instance.width
	}

	// Checks if a proposed width on an element ends up overlapping the other elements
	private def isWidthOverlapping(element: Element, width: Int): Boolean = {
		var otherElements = elementsExcept(element)

		// Filter all the elements that aren't to the left of our element
		otherElements = otherElements.filterNot(e => (e.x + e.width) < element.x)

		// Filter all the elements that aren't to the right of our element
		otherElements = otherElements.filterNot(e => e.x > element.x + width)

		// If any elements remain in this list, they overlap with our proposed element change
		otherElements.nonEmpty
	}

	// Checks if a proposed height is in the canvas and doesn't overlap anything
	private def isValidHeight(element: Element, height: Int): Boolean = {
		isHeightInCanvas(element, height) && isHeightOverlapping(element, height)
	}

	// Checks if a proposed height is within the canvas
	private def isHeightInCanvas(element: Element, height: Int): Boolean = {
		(element.y + height) <= Canvas.instance.height
	}

	// Checks if a proposed height on an element ends up overlapping the other elements
	private def isHeightOverlapping(element: Element, height: Int): Boolean = {
		var otherElements = elementsExcept(element)

		// Filter all the elements that aren't above (vertically up) from our element
		otherElements = otherElements.filterNot(e => (e.y + e.height) < element.y)

		// Filter all the elements that aren't below (vertically down) from our element
		otherElements = otherElements.filterNot(e => e.y > element.y +height)

		otherElements.nonEmpty
	}

	// Helper function to return the group(s) that contains a specified element
	private def groupWith(element: Element): List[Group] = {
		Canvas.instance.groups.filter(group => group.elements.contains(element))
	}

	// Helper function to return all the elements on the canvas besides our current one
	private def elementsExcept(element: Element): List[Element] = {
		Canvas.instance.elements.filterNot(_ == element)
	}

}

object WireframeBarricade {
	// Singleton implementation
	private val _instance = WireframeBarricade()
	def instance: WireframeBarricade = _instance
}
