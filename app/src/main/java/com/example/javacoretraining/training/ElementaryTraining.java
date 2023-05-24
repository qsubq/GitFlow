package com.example.javacoretraining.training;

/**
 * Набор тренингов по работе с примитивными типами java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ElementaryTrainingTest.
 */
public class ElementaryTraining {

    /**
     * Метод должен возвращать среднее значение
     * для введенных параметров
     *
     * @param firstValue  первый элемент
     * @param secondValue второй элемент
     * @return среднее значение для введенных чисел
     */
    public double averageValue(int firstValue, int secondValue) {
        return (firstValue + secondValue) / 2.0;
    }

    /**
     * Пользователь вводит три числа.
     * Произвести манипуляции и вернуть сумму новых чисел
     *
     * @param firstValue  увеличить в два раза
     * @param secondValue уменьшить на три
     * @param thirdValue  возвести в квадрат
     * @return сумма новых трех чисел
     */
    public double complicatedAmount(int firstValue, int secondValue, int thirdValue) {
        firstValue = firstValue * 2;
        secondValue = secondValue - 3;
        thirdValue = (int) Math.pow(thirdValue, 2);
        return firstValue + secondValue + thirdValue;
    }

    /**
     * Метод должен поменять значение в соответствии с условием.
     * Если значение больше 3, то увеличить
     * на 10, иначе уменьшить на 10.
     *
     * @param value число для изменения
     * @return новое значение
     */
    public int changeValue(int value) {
        if (value > 3) {
            value = value + 10;
        } else {
            value = value - 10;
        }
        return value;
    }

    /**
     * Метод должен менять местами первую
     * и последнюю цифру числа.
     * Обрабатывать максимум пятизначное число.
     * Если число < 10, вернуть
     * то же число
     *
     * @param value число для перестановки
     * @return новое число
     */
    public int swapNumbers(int value) {
        if (value > 99999) {
            return 0;
        }
        if (value < 10) {
            return value;
        } else {
            int sign = Integer.signum(value);
            value *= sign;

            int last = value % 10;
            int n = value / 10;
            int s = 0;
            for (int p = 1; n > 10; n /= 10, last *= 10, p *= 10) {
                s += p * (n % 10);
            }
            return sign * (10 * (last + s) + n);
        }
    }

    /**
     * Изменить значение четных цифр числа на ноль.
     * Счет начинать с единицы.
     * Обрабатывать максимум пятизначное число.
     * Если число < 10 вернуть
     * то же число.
     *
     * @param value число для изменения
     * @return новое число
     */
    public int zeroEvenNumber(int value) {
        if (value > 99999) {
            return 0;
        }
        int result = 0;
        if (value >= 10)
            result = zeroEvenNumber(value / 10) * 10;
        if (value % 2 != 0)
            result += value % 10;
        return result;
    }
}
