package gudmundsson.com.invoice.util;

import org.slf4j.Logger;

public class AElog {

	// Los println se deben habilitar solo en ambientes de Desarrollo y/o QA
    public static void println1(String msg) {
        System.out.println(msg);
    }

    public static void println2(String msg) {
        System.out.println(msg);
    }

    public static void println3(String msg) {
        System.out.println(msg);
    }

    // --------------------------- DEBUG ---------------------------
    public static synchronized void debugX(Logger logger, String msg) {
        logger.debug(msg);
    }

    public static synchronized void debugX(Logger logger, String msg, Throwable t) {
        logger.debug(msg, t);
    }

    // --------------------------- INFO ---------------------------
    public static synchronized void infoX(Logger logger, String msg) {
        logger.info(msg);
    }

    public static synchronized void infoX(Logger logger, String msg, Throwable t) {
        logger.info(msg, t);
    }

    // --------------------------- WARNING ---------------------------
    public static synchronized void warnX(Logger logger, String msg) {
        logger.warn(msg);
    }

    public static synchronized void warnX(Logger logger, String msg, Throwable t) {
        logger.warn(msg, t);
    }

    // --------------------------- ERROR ---------------------------
    public static synchronized void errorX(Logger logger, String msg) {
        logger.error(msg);
    }

    public static synchronized void errorX(Logger logger, String msg, Throwable t) {
        logger.error(msg, t);
    }
}
