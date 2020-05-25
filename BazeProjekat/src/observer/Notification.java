package observer;

import observer.enums.NotificationCode;

public class Notification {
	private NotificationCode code;
    private Object data;
    
    public Object getData() {
    	return data;
    }
    
    public NotificationCode getCode() {
    	return code;
    }
    
    public Notification(NotificationCode code, Object data) {
    	this.code = code;
    	this.data = data;
    }
    
    public Notification() {
    	
    }
    
}
