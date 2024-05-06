package gudmundsson.com.invoice.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * AElog
 *
 * @author Elio Arias
 * @since 1.0
 */
public class AEsnippet {

	public static java.sql.Date getCurrentDateSQL() {
        return new java.sql.Date(new Date().getTime());
    }

    public static java.sql.Date getCurrentDateSQL(long addTimeMillis) {
        return new java.sql.Date(new Date().getTime() + addTimeMillis);
    }

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    public static Timestamp getCurrentTimestamp(long addTimeMillis) {
        return new Timestamp(new Date().getTime() + addTimeMillis);
    }

    public static Timestamp getCurrentTimestamp(boolean isStart, int plusDays) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(GregorianCalendar.DAY_OF_MONTH, plusDays);
        if (isStart) {
            gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
            gc.set(GregorianCalendar.MINUTE, 0);
            gc.set(GregorianCalendar.SECOND, 0);
            gc.set(GregorianCalendar.MILLISECOND, 0);
        } else {
            gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
            gc.set(GregorianCalendar.MINUTE, 59);
            gc.set(GregorianCalendar.SECOND, 59);
            gc.set(GregorianCalendar.MILLISECOND, 999);
        }
        return new Timestamp(gc.getTimeInMillis());
    }

    public static Timestamp getCurrentTimestampRandom(int rangoMin) {
        return new Timestamp(new Date().getTime() + (getIntegerRandomNumber0N(rangoMin + 1) * 60 * 1000));
    }

    public static int getIntegerRandomNumber1N(int rango) {
        return (int) Math.floor(Math.random() * rango + 1);
    }

    public static int getIntegerRandomNumber0N(int rango) {
        return (int) Math.floor(Math.random() * rango);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Convierte un String a java.sql.Date
     * https://www.baeldung.com/java-string-to-timestamp
     * https://www.baeldung.com/java-string-to-date
     * 
     * @param stringDate Fecha a convertir en formato texto. (eg. "2019-04-24
     *                   11:27:38.000")
     * @param formatDate Formato de la fecha a convertir. (eg. "yyyy-MM-dd
     *                   hh:mm:ss.SSS")
     * @return
     */
    public static java.sql.Date convertStringToSqlDate(String stringDate, String formatDate, boolean isLenient) {
        java.sql.Date parsedDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatDate);
            dateFormat.setLenient(isLenient);
            parsedDate = new java.sql.Date(dateFormat.parse(stringDate).getTime());
        } catch (Exception e) { // this generic but you can control another types of exception
            parsedDate = null;
        }
        return parsedDate;
    }

    /**
     * Convierte un String a java.sql.TimeStamp
     * https://www.baeldung.com/java-string-to-timestamp
     * https://www.baeldung.com/java-string-to-date
     * 
     * @param stringDate Fecha a convertir en formato texto. (eg. "2019-04-24
     *                   11:27:38.000")
     * @param formatDate Formato de la fecha a convertir. (eg. "yyyy-MM-dd
     *                   hh:mm:ss.SSS")
     * @param type       Tipo de formato (0: default (00:00:00.0), 1: time
     *                   end(23:59:59.999))
     * @param isLenient  False si se quiere que la validacion sea estricta True si
     *                   se desea que trate de interpretar
     * @return
     */
    public static java.sql.Timestamp convertStringToSqlTimestamp(String stringDate, String formatDate, int type,
            boolean isLenient) {
        java.sql.Timestamp parsedTimestamp = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatDate);
            dateFormat.setLenient(isLenient);
            java.util.Date parsedDate = dateFormat.parse(stringDate);
            if (type == 1) {
                parsedTimestamp = new java.sql.Timestamp(parsedDate.getTime() + 86399999);
            } else {
                parsedTimestamp = new java.sql.Timestamp(parsedDate.getTime());
            }
        } catch (Exception e) { // this generic but you can control another types of exception
            parsedTimestamp = null;
        }
        return parsedTimestamp;
    }

    public static java.sql.Timestamp convertStringToSqlTimestamp(String stringDate) {
        java.sql.Timestamp parsedTimestamp = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            // dateFormat.setLenient(true);
            java.util.Date parsedDate = dateFormat.parse(stringDate);
            parsedTimestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch (Exception e) { // this generic but you can control another types of exception
            parsedTimestamp = null;
        }
        return parsedTimestamp;
    }

    /**
     * Devuelve la fecha actual como texto.
     * 
     * @param dateFormat formato del texto (Ej. yyyy-MM-dd HH:mm:ss)
     * @return Fecha con formato
     */
    public static String getCurrentDate(String dateFormat) {
        DateFormat fecha = new SimpleDateFormat(dateFormat);
        return fecha.format(new Date());
    }

    /**
     * Devuelve la fecha actual +/- dias como texto.
     * 
     * @param dateFormat formato del texto (Ej. yyyy-MM-dd HH:mm:ss)
     * @return Fecha con formato
     */
    public static String getCurrentDateX(String dateFormat, int days) {
        DateFormat fecha = new SimpleDateFormat(dateFormat);
        return fecha.format(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(days)));
    }

    /**
     * Devuelve una fecha como texto con formato.
     * 
     * @param timeStamp  fecha a transformar
     * @param dateFormat formato del texto (Ej. yyyy-MM-dd HH:mm:ss)
     * @return Fecha con formato
     */
    public static String getTimeStampToString(Timestamp timeStamp, String dateFormat) {
        DateFormat fecha = new SimpleDateFormat(dateFormat);
        return fecha.format(timeStamp);
    }

    public static int toInt(String value, int error) {
        int res = error;
        try {
            res = Integer.parseInt(value);
        } catch (Exception e) {
            res = error;
        }
        return res;
    }

    public static boolean isMsisdnOK(String msisdn) {
        return msisdn.matches("\\+?(591)?(\\d{8})");
    }

    public static String setTextInRange(String text, int maxLength) {
        return setTextInRange(text, maxLength, null);
    }

    public static String setTextInRange(String text, int maxLength, String defaultForNull) {
        if (text != null) {
            if (text.length() > maxLength) {
                text = text.substring(0, maxLength);
            }
        } else {
            text = defaultForNull;
        }
        return text;
    }

    public static String dateToString(Date date, String stringDateFormat) {
        DateFormat dateFormat = new SimpleDateFormat(stringDateFormat);
        String dateAsString = dateFormat.format(date);
        return dateAsString;
    }

    public static void main(String[] args) {
        System.out.println("----");
        System.out.println(getCurrentDate("yyyyMMdd"));
        System.out.println(getCurrentDate("yyMMdd"));
        System.out.println(getCurrentDate("yy/MM/dd  HH:mm:ss"));
        System.out.println("----");
        System.out.println(convertStringToSqlTimestamp("20190714235059"));
        System.out.println(convertStringToSqlTimestamp("20190715005059"));
        System.out.println(convertStringToSqlTimestamp("20190715015059"));
        System.out.println("-----");
        System.out.println(convertStringToSqlDate("2022-01-33", "yyyy-MM-dd", false));
        System.out.println(convertStringToSqlDate("2022-01-02", "yyyy-MM-dd", false));
        System.out.println(convertStringToSqlDate("2022-01-2", "yyyy-MM-dd", false));
        System.out.println("----");
        System.out.println(getTimeStampToString(new Timestamp(new Date().getTime()), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(getTimeStampToString(new Timestamp(new Date().getTime()), "yyyy-MM-dd hh:mm:ss"));
        System.out.println("----");
        System.out.println(getCurrentDateX("dd-MM-YYYY", -1));
        System.out.println(getCurrentDateX("dd-MM-yyyy", -1));
        System.out.println(getCurrentDateX("yy-MM-dd  HH:mm:ss", -1));
        System.out.println("----");
        Timestamp timestamp = getCurrentTimestamp();
        System.out.println("timestamp" + timestamp);
        System.out.println("timestamp" + timestamp.toInstant());
        timestamp.setTime(timestamp.getTime() + (1000 * 60 * 60 * 24));
        System.out.println(timestamp);
        System.out.println("----");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime0: " + localDateTime);
        System.out.println("localDateTime1: " + localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("localDateTime2: " + localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println("localDateTime3: " + localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        // System.out.println("localDateTime4: " +
        // localDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        // System.out.println("localDateTime5: " +
        // localDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        // System.out.println("localDateTime6: " +
        // localDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME));
        // System.out.println("localDateTime7: " +
        // localDateTime.format(DateTimeFormatter.ISO_INSTANT));
        System.out.println("----");
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("zonedDateTime0: " + zonedDateTime);
        System.out.println("zonedDateTime1: " + zonedDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("zonedDateTime2: " + zonedDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println("zonedDateTime3: " + zonedDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println("zonedDateTime4: " + zonedDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        System.out.println("zonedDateTime5: " + zonedDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        System.out.println("zonedDateTime6: " + zonedDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME));
        System.out.println("zonedDateTime7: " + zonedDateTime.format(DateTimeFormatter.ISO_INSTANT));
        System.out.println(
                "zonedDateTime8: " + zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")));
        System.out.println(
                "zonedDateTime9: " + zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
        System.out.println("----");
        Instant instant = Instant.parse("2020-08-24T21:49:31.702Z");
        System.out.println("instant: " + instant);
        System.out.println("instant: " + instant.atZone(ZoneId.systemDefault())); // -04:00
        System.out.println("instant: " + instant.atZone(ZoneId.of("America/Sao_Paulo"))); // -03:00
        System.out.println("----");
        zonedDateTime = ZonedDateTime.parse("2020-08-24T21:49:31.702+01:00");
        System.out.println("zonedDateTime: " + zonedDateTime);
        System.out.println("zonedDateTime: " + zonedDateTime.withZoneSameInstant(ZoneId.systemDefault())); // -04:00
        System.out.println("zonedDateTime: " + zonedDateTime.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"))); // -03:00
        System.out.println("----");
    }
}
