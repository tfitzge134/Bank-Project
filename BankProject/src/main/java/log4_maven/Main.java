package log4_maven;
import org.apache.log4j.Logger;

public class Main {
private static Logger log = Logger.getLogger(Main.class);
	public static void main(String[] args) {
	log.trace("Hello from Trace");
	log.debug("Hello from Debug");
	log.info("hello from Info");
	log.warn("hello from warn");
	log.error("hello from Error");
	log.fatal("hello from fatal");
		

	}

}
