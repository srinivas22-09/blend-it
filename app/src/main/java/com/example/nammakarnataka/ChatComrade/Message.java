//package com.example.nammakarnataka.ChatComrade;
//
//public class Message {
//    public static String SENT_BY_ME="me";
//    public static String SENT_BY_BOT="bot";
//    String message;
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public String getSentby() {
//        return sentby;
//    }
//
//    public void setSentby(String sentby) {
//        this.sentby = sentby;
//    }
//
//    String sentby;
//
//    public Message(String message, String sentby) {
//        this.message = message;
//        this.sentby = sentby;
//    }
//}

package com.example.nammakarnataka.ChatComrade;

public class Message {
    public static String SENT_BY_ME = "me";
    public static String SENT_BY_BOT="bot";

    String message;
    String sentBy;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public Message(String message, String sentBy) {
        this.message = message;
        this.sentBy = sentBy;
    }
}
