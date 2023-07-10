package com.example.javacoretraining.training.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ClassesSecondTask {
    int[] array;

    ClassesSecondTask(int amountOfItems){
        array = new int[amountOfItems];
    }

    public void fillArray(){
        Random random = new Random();
        for(int i = 0; i < array.length; i++){
            array[i] = random.nextInt();
        }
    }

    public void shuffleArray(){
        Collections.shuffle(Collections.singletonList(array));
    }

    public void showArray(){
        System.out.print(Arrays.toString(array));
    }

}
