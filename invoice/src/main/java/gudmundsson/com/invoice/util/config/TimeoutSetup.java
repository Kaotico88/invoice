package gudmundsson.com.invoice.util.config;

public class TimeoutSetup {

	private int connectTimeout;

    private int requestTimeout;

    private int readTimeout;

    public TimeoutSetup(int connectTimeout, int requestTimeout, int readTimeout) {
        this.connectTimeout = connectTimeout;
        this.requestTimeout = requestTimeout;
        this.readTimeout = readTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
}
