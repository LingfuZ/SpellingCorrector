package spell;

import java.io.IOException;

/**
 * Created by Lingfu on 5/6/2014.
 */
public class Spell implements SpellCorrector {

    // Fields
//    A data structure to store similar words

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {

    }

    @Override
    public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {



        return null;
    }

    public static void main(String[] args) {
        /*
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
