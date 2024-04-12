package gudmundsson.com.invoice.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
//import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AEutil {

	final static Logger logger = LoggerFactory.getLogger(AEutil.class);

    @Autowired
    private Environment environment;

    private String localhost;

    private String inetAddress;

    private String port;

    private String localhostPort;

    private String inetAddressPort;

    public void loadData() {
        port = environment.getProperty("local.server.port");
        localhost = environment.getProperty("java.rmi.server.hostname");
        try {
            inetAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            inetAddress = "localhost";
            e.printStackTrace();
        }
        localhostPort = localhost + ":" + port;
        inetAddressPort = inetAddress + ":" + port;
        AElog.infoX(logger, localhostPort);
        AElog.infoX(logger, inetAddressPort);
    }

    public String getClientIpAddress(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getLocalhost() {
        return localhost;
    }

    public void setLocalhost(String localhost) {
        this.localhost = localhost;
    }

    public String getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(String inetAddress) {
        this.inetAddress = inetAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getLocalhostPort() {
        return localhostPort;
    }

    public void setLocalhostPort(String localhostPort) {
        this.localhostPort = localhostPort;
    }

    public String getInetAddressPort() {
        return inetAddressPort;
    }

    public void setInetAddressPort(String inetAddressPort) {
        this.inetAddressPort = inetAddressPort;
    }
}
