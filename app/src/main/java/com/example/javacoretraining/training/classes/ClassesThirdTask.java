package com.example.javacoretraining.training.classes;

public class ClassesThirdTask {
    int a,b,c;

    ClassesThirdTask(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int perimeter(){
        return a + b + c;
    }

    public int square(int height){
        return a * height / 2;
    }

    public ClassesThirdTask newObject(int a, int b, int c){
        return new ClassesThirdTask(a, b, c);
    }

}
