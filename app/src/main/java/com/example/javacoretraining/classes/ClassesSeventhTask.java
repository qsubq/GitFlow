package com.example.javacoretraining.classes;

public class ClassesSeventhTask {
}

interface Product{}
interface Order{}

interface Buyer{

    void addProductInformation(Product product, String information);
    void registerOrder(Order order, Client client);
    void addInBlackList(Client client);
}

interface Client{
    Order makeOrder(Product[] products);
}
