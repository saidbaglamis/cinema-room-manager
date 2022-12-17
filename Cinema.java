// =================================================
// AUTHOR       : @saidbaglamis 
// CREATE DATE  : 16.12.2022
// PURPOSE      : Personal project
// =================================================

import java.util.Scanner;

public class Cinema {

    static Scanner scanner = new Scanner(System.in);
    static int rows;
    static int seats;
    static int income;
    static int price;
    static int rowNumber;
    static int seatNumber;
    static int frontRow;
    static int backRow;
    static int selection;
    static char[][] seatArray;
    static int purchasedTickets = 0;
    static int currentIncome = 0;
    static double percentage;


    public static void main(String[] args) {

        getCinemaSize();
        do {
            showMenu();
            selection = scanner.nextInt();
            selectedMenu(selection);
        } while (selection != 0);
    }
    public static void sellTicket() {
        if (seatArray[rowNumber - 1][seatNumber - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            buyTicket();
        } else {
            seatArray[rowNumber - 1][seatNumber - 1] = 'B';
            purchasedTickets++;
            currentIncome += price;
            System.out.println("Ticket price: $" + price);
        }
    }

    public static void buyTicket(){
        System.out.println("Enter a row number:");
        rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        seatNumber = scanner.nextInt();
        if (rowNumber > rows || rowNumber <= 0 || seatNumber > seats || seatNumber <= 0) {
            System.out.println("Wrong input!");
            buyTicket();
        }
        calculatePrice();
        sellTicket();
    }

    public static void selectedMenu(int selection) {
        if (selection == 1){
             printAvailableSeats();
        } else if (selection == 2) {
            buyTicket();
        } else if (selection == 3) {
            getStatistics();
        }
    }

    public static void showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void calculatePrice(){
        if (rows * seats <= 60) {
            price = 10;
        } else if (rowNumber < rows / 2.0) {
            price = 10;
        } else {
            price = 8;
        }
    }

    public static void getCinemaSize () {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        seatArray = new char[rows + 1][seats + 1];
        setCinema();
    }

    public static void getStatistics() {
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", getPercentage());
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + calculateIncome());
    }

    public static double getPercentage() {
        percentage = purchasedTickets * 100.0 / (rows * seats);
        return purchasedTickets == 0 ? 0 : percentage;
    }

    public static int calculateIncome () {
        if (rows * seats <= 60) {
            income = rows * seats * 10;
        } else if (rows % 2 != 0) {
            frontRow = (rows - 1) / 2;
            backRow = (rows + 1) / 2;
            income = frontRow * 10 * seats + backRow * 8 * seats;
        } else {
            income = (rows / 2) * 10 * seats + (rows / 2) * 8 * seats;
        }
        return income;
    }

    public static void setCinema() {
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++){
                seatArray[i][j] = 'S';
            }
        }
    }
    public static void printAvailableSeats() {
        System.out.println("Cinema:");
        for (int i = 0; i <= rows; i++) {
                if(i != 0) {
                    System.out.print(i + " ");
                }
                for (int j = 1; j <= seats; j++) {
                    if (i == 0 && j == 1) {
                        System.out.print("  ");
                    }
                    if (i == 0){
                        System.out.print(j + " ");
                    } else if (i == rowNumber && j == seatNumber) {
                        System.out.print(seatArray[i - 1][j - 1] + " ");
                    } else {
                        System.out.print(seatArray[i - 1][j - 1] + " ");
                    }
                }
                System.out.println();
        }
    }
}