package spell;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Lingfu on 5/6/2014.
 */
public class Spell implements SpellCorrector {

    // Fields
//    A data structure stores similar words
//    TreeSet is a great option because it doesn't contain duplicates and keep string in alphabetical order
    Set<String> similarWords = new TreeSet<String>();
//    Tire contain every word from dictionary file
    Trie dictionary = new Words();

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        /**
         * Scan the file
         * For each word in dictionary file, add the lowercase to Words
         */

        File dictionaryFile = new File(dictionaryFileName);
        Scanner scanner = new Scanner(dictionaryFile);

        while (scanner.hasNext()) {
            String word = scanner.next();
            dictionary.add(word.toLowerCase());
        }

    }

    @Override
    public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {

        /**
         * Check if input word exists in dictionary file
         * If yes then return the input word
         * If no then edit the input word and add them to similar words
         * Check if there are similar words exists in dictionary
         * If yes then return "most" similar word
         * If no then edit each old similar word then add them to new similar words
         * Check if there are new similar words exists in dictionary
         * If yes then return "most" similar word
         * If no then return NoSimilarWordFoundException
         */

        if (dictionary.find(inputWord) != null) {
            return inputWord;
        }

        edit(inputWord);

        String result = findMostSimilarWord();

        if (result == null) {
            Set<String> similarWordsCopy = new TreeSet<String>();
            similarWordsCopy.addAll(similarWords);

            similarWords = new TreeSet<String>();
            if (similarWords.size() != 0) {
                System.out.println("Error: similar words not reset before edit for distance 2.");
            }
            for (String word: similarWordsCopy) {
                edit(word);
            }

            result = findMostSimilarWord();
            if (result == null) {
                throw new NoSimilarWordFoundException();
            }
        }

        return result;
    }

    /**
     * For every similar words check if it exist in dictionary
     * if the word exists and have highest frequency then return this word
     * if two words have same frequency then return one with higher alphabetical order
     *
     * @return the most similar word or null
     */
    private String findMostSimilarWord() {

        int highestFrequency = 0;
        String result = null;

        for (String similarWord: similarWords) {
            Trie.Node found = dictionary.find(similarWord);
            int frequency = found.getValue();
            if (found != null && frequency > highestFrequency) {
                highestFrequency = frequency;
                result = similarWord;
            }
        }

        return result;
    }

    /**
     * Edit word with edit distance 1
     * Edit includes Deletion, Transposition, Alteration, and Insertion
     *
     * @param word
     */
    private void edit(String word) {
        performDeletion(word);
//        performTransposition(word);
        performAlteration(word);
        performInsertion(word);
    }

    /**
     * Add every possible word combination by deleting one character of word to similar words
     *
     * @param word
     */
    private void performDeletion(String word) {

        for( int i = 0; i < word.length(); i++) {

            StringBuilder original = new StringBuilder(word);
            String edited = original.deleteCharAt(i).toString();
            similarWords.add(edited);

        }

    }

    /**
     * Generate every possible word combination by switching adjacent characters
     * Add these new words into similar words
     *
     * @param word
     */
    private void performTransposition(String word) {

        StringBuilder modified = new StringBuilder(word);
        for (int i = 0; i < word.length(); i++) {
            char left = word.charAt(i);
            char right = word.charAt(i+1);

            modified.setCharAt(i+1, left);
            modified.setCharAt(i, right);

            similarWords.add(modified.toString());
        }

    }

    /**
     * Generate every possible word combination by changing one character to a different one
     * Add these new words into similar words
     * @param word
     */
    private final char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private void performAlteration(String word) {

        StringBuilder modified = new StringBuilder(word);
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < alphabet.length; j++) {
                modified.setCharAt(i, alphabet[j]);
                similarWords.add(modified.toString());
            }
        }

    }

    /**
     * Generate every possible word combination by adding one character
     * Add these new words into similar words
     * @param word
     */
    private void performInsertion(String word) {

        StringBuilder modified = new StringBuilder(word);
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < word.length(); j++) {
                modified.insert(i, alphabet[j]);
                similarWords.add(modified.toString());
            }
        }

    }

    @Override
    public String toString() {
        return dictionary.toString();
    }

    public static void main(String[] args) throws IOException, NoSimilarWordFoundException {
        /**
        *   Take first user input as the dictionary file
        *   The second user input is the target word which program will correct its spelling
        *
        *   Check user's input are legal.
        *
        *   Create a Spell object
        *   Let Spell object to use the dictionary file
        *   Ask Spell object to suggest a similar word
        *   Then print the word to console
        *
         */

        final int NUMBER_OF_ARGS = 2;

        if (args.length != NUMBER_OF_ARGS) {
            System.out.println("Error: The number of the arguments is wrong.");
            return;
        }

        final String dictionaryFileName = args[0];
        final String inputWord = args[1];

        SpellCorrector spell = new Spell();
        spell.useDictionary(dictionaryFileName);

        String outputFile = "dictionary.txt";
        PrintWriter writer = new PrintWriter(outputFile);
        writer.print(spell);
        writer.close();

        String result = spell.suggestSimilarWord(inputWord);

        System.out.println(result);
    }

}
