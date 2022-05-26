package util;

public class MoveResponse {
    private boolean successful;
    private String responseMessage;

    public MoveResponse(boolean successful, String message) {
        this.successful = successful;
        this.responseMessage = message;
    }
    
    public boolean wasSuccessfull() {
        return successful;
    }

    public String getMessage() {
        return responseMessage;
    }
}
