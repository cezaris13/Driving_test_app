package com.example.pijus.regitrosklausimynas;


public class UserModel {
    boolean isSelected;
    String userName;
    String True_val;
    String False_val;

    public UserModel(boolean isSelected, String userName,String true_val,String false_val) {
        this.isSelected = isSelected;
        this.userName = userName;
        this.False_val=false_val;
        this.True_val=true_val;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrue_val() {
        return True_val;
    }

    public void setTrue_val(String true_val) {
        True_val = true_val;
    }

    public String getFalse_val() {
        return False_val;
    }

    public void setFalse_val(String false_val) {
        False_val = false_val;
    }

}