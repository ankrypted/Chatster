package com.example.chatster;

public class DatabaseElements {
    private String nameVal;
    private Long ph;
    private String userName;
    private String passWord;
    private String Message = "";

    public DatabaseElements() {
    }

    public String getNameVal() {
        return nameVal;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
    public void setMessage() {
        Message = "";
    }

    public void setNameVal(String nameVal) {
        this.nameVal = nameVal;
    }

    public Long getPh() {
        return ph;
    }

    public void setPh(Long ph) {
        this.ph = ph;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
