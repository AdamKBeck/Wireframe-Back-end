package wireframe

import org.scalatest.FlatSpec
import java.io._

class LoggerTest extends FlatSpec {
	behavior of "log"
	it should "write to a file" in {
		Logger.instance.log("sample message", "loggertest.txt")
		assert(new File("src/loggertest.txt").exists())
	}
}
