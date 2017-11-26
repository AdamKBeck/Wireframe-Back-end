trait Lockable {
	def locked: Boolean
	def locked_=(value: Boolean): Unit
}
