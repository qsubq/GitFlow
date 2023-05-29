package com.example.javacoretraining.classes;

public class ClassesFifthTask {
    private int ID;
    private String name;
    private String lastName;
    private String middleName;
    private String address;
    private int creditCartNumber;
    private int debit;
    private int credit;
    private int timeLongDistanceCalls;
    private int timeShortDistanceCalls;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCreditCartNumber() {
        return creditCartNumber;
    }

    public void setCreditCartNumber(int creditCartNumber) {
        this.creditCartNumber = creditCartNumber;
    }

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getTimeLongDistanceCalls() {
        return timeLongDistanceCalls;
    }

    public void setTimeLongDistanceCalls(int timeLongDistanceCalls) {
        this.timeLongDistanceCalls = timeLongDistanceCalls;
    }

    public int getTimeShortDistanceCalls() {
        return timeShortDistanceCalls;
    }

    public void setTimeShortDistanceCalls(int timeShortDistanceCalls) {
        this.timeShortDistanceCalls = timeShortDistanceCalls;
    }

    ClassesFifthTask(int ID, String name,
                     String lastName,
                     String middleName,
                     String address,
                     int creditCartNumber,
                     int debit,
                     int credit,
                     int timeLongDistanceCalls,
                     int timeShortDistanceCalls) {

        this.ID = ID;
        this.name = name;
        this.lastName = name;
        this.middleName = middleName;
        this.address = address;
        this.creditCartNumber = creditCartNumber;
        this.debit = debit;
        this.credit = credit;
        this.timeLongDistanceCalls = timeLongDistanceCalls;
        this.timeShortDistanceCalls = timeShortDistanceCalls;
    }

    public void printInfo(){
        System.out.println(ID);
        System.out.println(name);
        System.out.println(lastName);
        System.out.println(middleName);
        System.out.println(address);
        System.out.println(creditCartNumber);
        System.out.println(debit);
        System.out.println(credit);
        System.out.println(timeLongDistanceCalls);
        System.out.println(timeShortDistanceCalls);
    }

}
