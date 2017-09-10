package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int SIZE = 3;        //размер поля в данном случае 3х3
    public static char DOT_EMPTY = '*';     //обозначения поля
    public static char DOT_X = 'X';          //знак какие будет ход
    public static char DOT_O = 'O';         //знак какие будет ход

    public static char[][] map;           //создание игрового поля из массива

    public static Scanner sc = new Scanner(System.in);//назначение сканера(ввод данных пользователя
    public static Random rd = new Random();

    public static void main(String[] args) {
        initMap();
        System.out.println("Кто начнет");
        System.out.println("1-я");
        System.out.println("2-комп");
        int a = sc.nextInt();
        if (a == 1){
            aiTurn();
        }
        else {
            humanTurn();
        }
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (isMapFull())break;
            if (checkWin(DOT_X)) {
                System.out.println("lose");
                break;
            }
            aiTurn();
            printMap();
            if (isMapFull()) break;
            if (checkWin(DOT_O)){
                System.out.println("win");
                break;
            }
        }
        System.out.println("Game over");
    }

    public static boolean checkWin(char ox){ //проверкка на победу по разным плоскостям
        if (map[0][0] == ox && map[0][1] == ox &&  map[0][2] == ox) return true;
        if (map[1][0] == ox && map[1][1] == ox &&  map[1][2] == ox) return true;
        if (map[2][0] == ox && map[2][1] == ox &&  map[2][2] == ox) return true;

        if (map[0][0] == ox && map[1][0] == ox &&  map[2][0] == ox) return true;
        if (map[0][1] == ox && map[1][1] == ox &&  map[2][1] == ox) return true;
        if (map[0][2] == ox && map[1][2] == ox &&  map[2][2] == ox) return true;

        if (map[0][0] == ox && map[1][1] == ox &&  map[2][2] == ox) return true;
        if (map[2][0] == ox && map[1][1] == ox &&  map[0][2] == ox) return true;

        return false;
    }

    public static boolean isMapFull(){ //действия в случае,если карта забита
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE ; j++) {
                if (map[i][j] == DOT_EMPTY )return false;
            }
        }
        return true;
    }

    public static void aiTurn(){ // ход компьютера
        int x ,y;
        do {
            x = rd.nextInt(SIZE);
            y = rd.nextInt(SIZE);
        }while (!isCellValid(x,y));
        System.out.println("Комп сходил");
        map[y][x]=DOT_O;
    }

    public static void humanTurn(){ // ход человека
    int x ,y;
    do {
       System.out.println("Ваш ход(ввод в формате Х,У");
        x = sc.nextInt() - 1;
        y = sc.nextInt() - 1;
        }while (!isCellValid(x,y));
    map[y][x]=DOT_X;
    }



    public static boolean isCellValid(int x, int y) {  //страховка на случай неправильного ввода чисел
        if(x<0||x>=SIZE||y<0||y>=SIZE)return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }


    public static void initMap(){  //объявление поля
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap(){  //печать поля
        for (int i = 0; i <= SIZE ; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            for (int i = 0; i < SIZE; i++) {
                System.out.print((i + 1) + " ");
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
        System.out.println();
        }

}
