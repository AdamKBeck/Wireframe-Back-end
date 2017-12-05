package wireframe

import java.io._

/**
  * EECS 293
  * Created by Adam Beck on 12/5/2017
  * akb93@case.edu
  * Version 1.0
  *
  * Logger: is used to log invalid attempts to place, resize, group, etc. elements. When a barricade branch is false,
  * the reason for the failure is logged through this class. The program keeps on running as if nothing happened (as in,
  * we never tried to place an element incorrectly in the first place) to keep failure atomicity
  *
  * Final, as nothing should extend this
  */
final case class Logger private() {
	/**
	  * Logs a message to the log file
	  *
	  * @param message the message to log to the log file
	  * @param fileName the filename of the logfile to work with
	  */
	def log(message: String, fileName:String = "log.txt"): Unit = {
		val pw = new PrintWriter(new FileWriter("src/" + fileName, true))
		pw.write(message + "\n")
		pw.close()
	}
}

object Logger {
	// Singleton implementation
	private val _instance = Logger()
	def instance = _instance

}