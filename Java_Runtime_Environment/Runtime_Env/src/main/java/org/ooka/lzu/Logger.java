package org.ooka.lzu;

// public interface Logger {
//     void sendLog(String message);
// }


public class Logger {
    
    public void sendLog(String message) {
        System.out.println("++++ LOG: " + message + " (" + System.currentTimeMillis() + ")");
    }
}