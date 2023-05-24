package com.example.javacoretraining.training;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Набор тренингов по работе с массивами в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ArraysTrainingTest.
 */
public class ArraysTraining {

    /**
     * Метод должен сортировать входящий массив
     * по возрастранию пузырьковым методом
     *
     * @param valuesArray массив для сортировки
     * @return отсортированный массив
     */
    public int[] sort(int[] valuesArray) {
        boolean sorted = false;
        int temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < valuesArray.length - 1; i++) {
                if (valuesArray[i] > valuesArray[i + 1]) {
                    temp = valuesArray[i];
                    valuesArray[i] = valuesArray[i + 1];
                    valuesArray[i + 1] = temp;
                    sorted = false;
                }
            }
        }
        return valuesArray;
    }

    /**
     * Метод должен возвращать максимальное
     * значение из введенных. Если входящие числа
     * отсутствуют - вернуть 0
     *
     * @param values входящие числа
     * @return максимальное число или 0
     */
    public int maxValue(int... values) {
        if (values == null) {
            return 0;
        } else if (values.length == 1) {
            return values[0];
        } else {
            int max = 0;
            for (int counter = 1; counter < values.length; counter++) {
                if (values[counter] > max) {
                    max = values[counter];
                }
            }
            return max;
        }
    }

    /**
     * Переставить элементы массива
     * в обратном порядке
     *
     * @param array массив для преобразования
     * @return входящий массив в обратном порядке
     */
    public int[] reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }

    /**
     * Метод должен вернуть массив,
     * состоящий из чисел Фибоначчи
     *
     * @param numbersCount количество чисел Фибоначчи,
     *                     требуемое в исходящем массиве.
     *                     Если numbersCount < 1, исходный
     *                     массив должен быть пуст.
     * @return массив из чисел Фибоначчи
     */
    public int[] fibonacciNumbers(int numbersCount) {
        if (numbersCount < 1) {
            return new int[0];
        } else {
            int[] arr = new int[numbersCount];
            arr[0] = 1;
            arr[1] = 1;
            for (int i = 2; i < arr.length; ++i) {
                arr[i] = arr[i - 1] + arr[i - 2];
            }
            return arr;
        }
    }

    /**
     * В данном массиве найти максимальное
     * количество одинаковых элементов.
     *
     * @param array массив для выборки
     * @return количество максимально встречающихся
     * элементов
     */
    public int maxCountSymbol(int[] array) {
        Map<Integer, Integer> hMap = new HashMap<>();
        HashSet<Integer> hSet = new HashSet<>();
        for (int i : array) {
            if (hSet.add(i)) {
                hMap.put(i, 1);
            } else {
                hMap.put(i, hMap.get(i) + 1);
            }
        }
        Iterator<Integer> iter = hMap.values().iterator();
        int temp = 0;
        while (iter.hasNext()) {
            int max = iter.next();
            if (max > temp) {
                temp = max;
            }
        }
        return temp;
    }
}

