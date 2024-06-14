package com.example.quickmaths;

public enum TypeOperation {
    ADD("+"),
    SUBSTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");
    private String symbole;

    TypeOperation(String s) {
        this.symbole = s;
    }

    public String getSymbole() {
        return symbole;
    }

    public TypeOperation setValue(int i) {
        switch (i) {
            case 2:
                return TypeOperation.SUBSTRACT;
            case 3:
                return TypeOperation.MULTIPLY;
            case 4:
                return TypeOperation.DIVIDE;
            default:
                return TypeOperation.ADD;
        }
    }
}