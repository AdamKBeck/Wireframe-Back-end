package wireframe

import java.io._
import java.nio.file._

/* This class is used to log invalid attempts to place, resize, group, etc. elements. When a barricade branch is false,
 * the reason for the failure is logged through this class. The program keeps on running as if nothing happened (as in,
 * we never tried to place an element incorrectly in the first place) to keep failure atomicity
 */
case class Logger private() {
	def log(message: String, fileName:String = "log.txt"): Unit = {
		val pw = new PrintWriter(new FileWriter("src/" + fileName, true))
		pw.write(message + "\n")
		pw.close()
	}
}

object Logger {
	// Singleton implementation, also delete our previous log each time the program runs
	private val _instance = Logger()

	def instance = _instance

}