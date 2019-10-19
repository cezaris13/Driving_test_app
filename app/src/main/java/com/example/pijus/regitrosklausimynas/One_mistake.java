package com.example.pijus.regitrosklausimynas;


public class One_mistake {
    boolean isSelected;
    String One_Mistake;
    String True_val;
    String False_val;

    public One_mistake(boolean isSelected, String mistake, String true_val, String false_val) {
        this.isSelected = isSelected;
        this.One_Mistake = mistake;
        this.False_val = false_val;
        this.True_val = true_val;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getOne_Mistake() {
        return One_Mistake;
    }

    public void setOne_Mistake(String one_Mistake) {
        One_Mistake = one_Mistake;
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
