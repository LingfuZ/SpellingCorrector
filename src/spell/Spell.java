package spell;

import java.io.IOException;
import java.util.List;
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


    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        /**
         * Scan the file
         * Create Words
         * For each word in dictionary file, add it to Words object
         *
         */
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

        return null;
    }

    private List<String> checkDictionary() {
        return null;
    }

    private String findMostSimilarWord(List<String> condidates) {
        return null;
    }

    public static void main(String[] args) {
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
    }

}
