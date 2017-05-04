package com.lukasz;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException  {
        boolean quit = false;
        boolean win = false;
        char checkedChar;
        int step = 0;
        String myWord;
        SecretWord secretWord;

        System.out.println("Podaj wlasne haslo lub zostaw puste, zeby wylosowac.");
        myWord = scanner.nextLine();
        if(myWord.equals("")) {
            secretWord = new SecretWord();
        } else {
            secretWord = new SecretWord(myWord);
        }


        while(!quit) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            drawTheHangman(step);
            System.out.println("Haslo:   " + secretWord.getHiddenWord() );
            if(win) {
                System.out.println("Gratulacje! Uniknales powieszenia.");
                break;
            }
            System.out.println("Twoja litera?");
            checkedChar = scanner.nextLine().charAt(0);

            if(checkedChar == '0') {
                System.out.println("Haslo to " + secretWord.getWord() );
                scanner.nextLine();
                continue;
            }

            if(secretWord.checkALetter(checkedChar)) {
                System.out.println(checkedChar + " wystepuje w hasle.");
            }else {
                System.out.println(checkedChar + " nie wystepuje w hasle.");
                step++;
            }

            win = secretWord.checkIfWinner();

            if(step == 8) {
                System.out.println("Przegrales! Haslem bylo " + secretWord.getWord());
                drawTheHangman(step);
                quit = true;
            }
        }
    }

    private static void guessALetter(char ch) {

    }

    private static void drawTheHangman(int step) {

        System.out.print("  ");
        if(step > 3) System.out.print("_____");
        else System.out.print("     ");
        System.out.println(" ");

        System.out.print("  ");
        if(step > 2) System.out.print("|");
        else System.out.print(" ");
        System.out.print("   ");
        if(step > 4) System.out.print("|");
        else System.out.print(" ");
        System.out.println(" ");

        System.out.print("  ");
        if(step > 2) System.out.print("|");
        else System.out.print(" ");
        System.out.print("   ");
        if(step > 5) System.out.print("o");
        else System.out.print(" ");
        System.out.println(" ");

        System.out.print("  ");
        if(step > 2) System.out.print("|");
        else System.out.print(" ");
        System.out.print("  ");
        if(step > 6) System.out.print("-|-");
        else System.out.print("   ");
        System.out.println();

        System.out.print("  ");
        if(step > 2) System.out.print("|");
        else System.out.print(" ");
        System.out.print("  ");
        if(step > 7) System.out.print("/ \\");
        else System.out.print("   ");
        System.out.println();

        System.out.print("  ");
        if(step > 2) System.out.print("|");
        else System.out.print(" ");
        System.out.println("     ");

        System.out.print(" ");
        if(step > 0) System.out.print("/");
        else System.out.print(" ");
        System.out.print(" ");
        if(step > 1) System.out.print("\\");
        else System.out.print(" ");
        System.out.println("    ");

        if(step > 0) System.out.print("/");
        else System.out.print(" ");
        System.out.print("   ");
        if(step > 1) System.out.print("\\");
        else System.out.print(" ");
        System.out.println("   ");

    }
}