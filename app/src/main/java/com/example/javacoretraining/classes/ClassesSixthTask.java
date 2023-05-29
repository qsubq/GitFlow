package com.example.javacoretraining.classes;

public class ClassesSixthTask {
    static class historicalFaculty implements Faculty{}
    static class historicalExam implements Exam{}

    static class karginMaksim implements Entrant{
        @Override
        public void facultyRegistration(Faculty faculty) {
            System.out.println("Зарегистрировался на факультет " + faculty.toString());
        }

        @Override
        public void passExam(Exam exam) {
            System.out.println("Прошёл экзамен " + exam.toString());
        }
    }

    static class historicalProfessor implements Professor{

        @Override
        public void setMark(int mark) {
            System.out.println("Выставил оценку " + mark);
        }
    }
}

interface Faculty{}
interface Exam{}
interface Entrant{
    void facultyRegistration(Faculty faculty);
    void passExam(Exam exam);
}
interface Professor{
    void setMark(int mark);
}
