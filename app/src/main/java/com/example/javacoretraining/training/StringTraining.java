package com.example.javacoretraining.training;


/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see StringsTrainingTest.
 */
public class StringTraining {

    /**
     * Метод по созданию строки,
     * состоящей из нечетных символов
     * входной строки в том же порядке
     * (нумерация символов идет с нуля)
     *
     * @param text строка для выборки
     * @return новая строка из нечетных
     * элементов строки text
     */
    public String getOddCharacterString(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < text.length(); i += 2) {
            result.append(text.charAt(i));
        }
        return result.toString();
    }

    /**
     * Метод для определения количества
     * символов, идентичных последнему
     * в данной строке
     *
     * @param text строка для выборки
     * @return массив с номерами символов,
     * идентичных последнему. Если таких нет,
     * вернуть пустой массив
     */
    public int[] getArrayLastSymbol(String text) {
        if(text.isEmpty()){
            return new int[0];
        }

        char lastChar = text.charAt(text.length() - 1);
        int count = 0;
        for (int i = 0; i < text.length()-1; i++) {
            if (text.charAt(i) == lastChar) {
                count++;
            }
        }
        int[] matchingChars = new int[count];
        int j = 0;
        for (int i = 0; i < text.length()-1; i++) {
            if (text.charAt(i) == lastChar) {
                matchingChars[j] = i;
                j++;
            }
        }
        return matchingChars;
    }

    /**
     * Метод по получению количества
     * цифр в строке
     *
     * @param text строка для выборки
     * @return количество цифр в строке
     */
    public int getNumbersCount(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    /**
     * Дан текст. Заменить все цифры
     * соответствующими словами.
     *
     * @param text текст для поиска и замены
     * @return текст, где цифры заменены словами
     */
    public String replaceAllNumbers(String text) {
        return text.replaceAll("0", "zero").replaceAll("1", "one");
    }

    /**
     * Метод должен заменить заглавные буквы
     * на прописные, а прописные на заглавные
     *
     * @param text строка для изменения
     * @return измененная строка
     */
    public String capitalReverse(String text) {
        char[] chArr = text.toCharArray();
        for (int i = 0; i < chArr.length; i++) {
            char ch = chArr[i];
            if (Character.isLowerCase(ch)) {
                chArr[i] = Character.toUpperCase(ch);
            } else if (Character.isUpperCase(ch)) {
                chArr[i] = Character.toLowerCase(ch);
            }
        }
        return new String(chArr);
    }
}
