package pl.java.scalatech.retry;

public class UnexpectedSituation extends Exception {

    private static final long serialVersionUID = 7063096710140339922L;

    public UnexpectedSituation() {
        super();
    }

    public UnexpectedSituation(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedSituation(String message) {
        super(message);
    }

    public UnexpectedSituation(Throwable cause) {
        super(cause);
    }

}
