package com.example.lib;



public class Main {
    public static void main(String[] args) {

        //FirstTask
        System.out.println("First task: ");
        Runnable myClosure = () -> System.out.println("I love Java");
        myClosure.run();
        repeatTask(10, myClosure);

        //SecondTask
        System.out.println("Second task: ");
        multipleSteps();


        //ThirdTask
        System.out.println("Third task: ");
        Shape square = new Square(9);
        System.out.println("Square perimeter: " + square.perimeter());
        System.out.println("Square area: " + square.area());

        Shape circle = new Circle(13);
        System.out.println("Circle perimeter: " + circle.perimeter());
        System.out.println("Circle area: " + circle.area());


    }

    //FirstTask
    public static void repeatTask(int times, Runnable task) {
        for (int i = 0; i < times; i++) {
            task.run();
        }
    }

    //SecondTask
    enum Directions {
        up, down, left, right
    }

    static int[] move(int[] location, Directions direction) {
        int[] newLocation = location.clone();
        switch (direction) {
            case up:
                newLocation[1]++;
                break;
            case down:
                newLocation[1]--;
                break;
            case left:
                newLocation[0]--;
                break;
            case right:
                newLocation[0]++;
                break;
        }
        return newLocation;
    }

    static void multipleSteps() {
        int[] location = {0, 0};
        Directions[] directions = {Directions.up, Directions.up, Directions.left, Directions.down, Directions.left,
                Directions.down, Directions.down, Directions.right, Directions.right, Directions.down, Directions.right}; // Последовательность направлений
        for (Directions direction : directions) {
            location = move(location, direction);
            System.out.println("(" + location[0] + "," + location[1] + ")");
        }
    }

    //ThirdTask
    public interface Shape {
        double perimeter();

        double area();
    }

    public static class Rectangle implements Shape {
        private final double width;
        private final double length;

        public Rectangle(double width, double length) {
            this.width = width;
            this.length = length;
        }

        public double perimeter() {
            return 2 * (width + length);
        }

        public double area() {
            return width * length;
        }
    }

    public static class Square implements Shape {
        private final double side;

        public Square(double side) {
            this.side = side;
        }

        public double perimeter() {
            return 4 * side;
        }

        public double area() {
            return side * side;
        }
    }

    public static class Circle implements Shape {
        private final double diameter;

        public Circle(double diameter) {
            this.diameter = diameter;
        }

        public double perimeter() {
            return Math.PI * diameter;
        }

        public double area() {
            return Math.PI * Math.pow(diameter / 2, 2);
        }
    }

}