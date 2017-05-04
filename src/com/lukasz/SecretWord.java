package com.lukasz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by Łukasz on 26.04.2017.
 */
public class SecretWord {
    private String word;
    private String hiddenWord;

    public SecretWord() throws FileNotFoundException, IOException {
        this.word = generateRandomWord();
        this.hiddenWord = hideWord();
    }

    public SecretWord(String myWord) {
        this.word = myWord;
        this.hiddenWord = hideWord();
    }

    public String getWord() {
        return this.word;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    private String hideWord() {
        int len = this.word.length();
        String hidden = "";
        for(int i = 0; i < len; i++) {
            hidden += "_";
        }
        return hidden;
    }

    private String generateRandomWord() throws FileNotFoundException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("slownik.txt"));

        for(int i = 0; i < randomNumber(); i++)
            bufferedReader.readLine();
        word = bufferedReader.readLine();
        return word;
    }

    private void showGuessedLetters(ArrayList<Integer> guessedLettersIndexes) {
        ListIterator iterator = guessedLettersIndexes.listIterator();
        String newHiddenWord = "";
        int position = 0;

        for(int i = 0; i < word.length(); i++) {
            if( iterator.hasNext() && i == guessedLettersIndexes.get(position) ) {
                newHiddenWord += word.charAt((int) iterator.next());
                position++;
            } else {
                newHiddenWord += hiddenWord.charAt(i);
            }
        }

        hiddenWord = newHiddenWord;
    }

    public boolean checkALetter(char checkedLetter) {
        Integer position = 0;
        ArrayList<Integer> indexes = new ArrayList<Integer>();

        for(; position < word.length(); position++) {
            if(this.word.charAt(position) == checkedLetter) {
                indexes.add(position);
            }
        }

        if (indexes.size() > 0) showGuessedLetters(indexes);

        return (indexes.size() > 0);
    }

    public boolean checkWord(String wordToGuess, String hiddenWord) {
        if(wordToGuess.equals(hiddenWord))
            return true;
        return false;
    }

    public boolean checkIfWinner() {
        return (word.equals(hiddenWord));
    }

    private int randomNumber() {
        Random generator = new Random();
        return generator.nextInt(14910);
    }


}
