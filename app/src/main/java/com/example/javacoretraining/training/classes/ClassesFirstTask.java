package com.example.javacoretraining.training.classes;

public class ClassesFirstTask {
    int firstValue,secondValue;

    ClassesFirstTask(int firstVal, int secondVal){
        firstValue = firstVal;
        secondValue = secondVal;
    }

    public void printValues(){
        System.out.println(firstValue);
        System.out.println(secondValue);
    }

    public void changeFirstValue(int newValue){
        firstValue = newValue;
    }

    public void changeSecondValue(int newValue){
        secondValue = newValue;
    }

    public int sumOfVariables(){
        return firstValue + secondValue;
    }
    public int largestValue(){
        return Math.max(firstValue, secondValue);
    }
}
