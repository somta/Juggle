package net.somta.juggle.core.executor.groovy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroovyLogPrinter {
    private static final Logger log = LoggerFactory.getLogger(GroovyLogPrinter.class);

    public static void debug(String msg) {
        log.debug(msg);
    }

    public static void info(String msg) {
        log.info(msg);
    }

    public static void warn(String msg) {
        log.warn(msg);
    }

    public static void error(String msg) {
        log.error(msg);
    }

}