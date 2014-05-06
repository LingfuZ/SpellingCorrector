package spell;

/**
 * Created by Lingfu on 5/6/2014.
 */
public class Words implements Trie {

//    Fields
//    number of unique words stored
    private int wordCount = -1;
//    number of word nodes contained
    private int nodeCount = -1;
//    root word node
    private WordNode root = new WordNode();

    public Words() {

    }

    @Override
    public void add(String word) {
        /**
         * Iterate through each character of the word
         * Check if root has word node for current character
         * Create a word node if there is none
         * Get the word node if there is existing one
         * Increment the node count after create a new node
         * After add the word, increment the word count
         */
    }

    @Override
    public Node find(String word) {

        /**
         * Similar to add method
         * Iterate through each character of thw word
         * Check if has node for current character
         * Return null if there is none
         * If last word node returns 0 count then return null
         * Otherwise return the last word node.
         */

        return null;
    }

    @Override
    public int getWordCount() {
        return wordCount;
    }

    @Override
    public int getNodeCount() {
        return nodeCount;
    }

    public class WordNode implements Trie.Node {

//      Fields
//        The size of the alphabet
        private final int ALPHABET_SIZE = 26;
//        An array represents alphabet list
        private WordNode alphabet[] = new WordNode[ALPHABET_SIZE];
//        count of how many times word appears
        private int count = -1;
//        lowercase a for finding the index of characters
        private final char LOWERCASE_A = 'a';

        public WordNode() {

        }

//        Return a word node in alphabet
        private WordNode getNode(char character) {

        }

//        Check if there is a word node exist at character index position
        private boolean hasNode() {

        }

//        Create a new word node at corresponding character index
        private void createNode(char character) {

        }

        @Override
        public int getValue() {
            return count;
        }
    }
}
