package spell;

/**
 * Created by Lingfu on 5/6/2014.
 */
public class Words implements Trie {

//    Fields
//    number of unique words stored
    private int wordCount = 0;
//    number of word nodes contained
    private int nodeCount = 0;
//    root word node
    private Node root = new WordNode();

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
         * After add the word, increment word frequency
         * If the word frequency is 0, increment the unique word count
         */

        Node currentNode = root;
        for (int i = 0; i<word.length(); i++) {
            char currentChar = word.charAt(i);
            if (currentNode.hasNode(currentChar)) {
                currentNode = currentNode.getNode(currentChar);
            } else {
                currentNode = currentNode.createNode(currentChar);
                nodeCount++;
            }
        }

        if (currentNode.getValue() == 0) {
            wordCount++;
        }
        currentNode.incrementValue();
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

        Node currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (currentNode.hasNode(currentChar)) {
                currentNode.getNode(currentChar);
            } else {
                return null;
            }
        }

        if (currentNode.getValue() != 0) {
            return currentNode;
        }

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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        return root.buildWord(result);
    }

    @Override
    public int hashCode() {
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    public class WordNode implements Trie.Node {

//      Fields

//        lowercase a for finding the index of characters
        private final char LOWERCASE_A = 'a';
//        An array represents alphabet list
        private Node alphabet[] = new WordNode[26];
//        count of how many times word appears
        private int count = 0;

        public WordNode() {

        }

//        Return a word node in alphabet
        @Override
        public Node getNode(char character) {
            /**
             * Get the index of input character
             * Return Node at index location
             */
            int index = character - LOWERCASE_A;
            return getAlphabet()[index];
        }

        @Override
        public void incrementValue() {
            count++;
        }

//        Check if there is a word node exist at character index position
        @Override
        public boolean hasNode(char character) {
            int index = character - LOWERCASE_A;
            Node target = getAlphabet()[index];
            if (target != null) {
                if (target.getClass() == this.getClass()) {
                    return true;
                }
            }
            return false;
        }

//        Create a new word node at corresponding character index
        @Override
        public Node createNode(char character) {
            /**
             * Get the index of the input character
             * Create a new word node object
             * Insert this word node in alphabet array at index position
             */
            int index = character - LOWERCASE_A;
            Node newNode = new WordNode();
            getAlphabet()[index] = newNode;
            return newNode;
        }

        @Override
        public int getValue() {
            return count;
        }

        private Node[] getAlphabet() {
            return alphabet;
        }

        @Override
        public String buildWord(StringBuilder path) {
            Node currentNode = this;

            for (int i = 0; i < alphabet.length; i++ ) {
                char currentChar = getCharFromA(i);
                if (currentNode.hasNode(currentChar)) {
                    currentNode = currentNode.getNode(currentChar);
                    path.append(currentChar);
                    currentNode.buildWord(path);
                    if (currentNode.getValue() > 0) {
                        path.append(" " + currentNode.getValue() + "\n");
                    }
                }
            }

            // Node doesn't have children
            if (currentNode.equals(this)) {
                if (currentNode.getValue() > 0) {
                    path.append(" " + currentNode.getValue() + "\n");
                    return path.toString();
                }
            }

            return path.toString();
        }

        private char getCharFromA(int distance) {
            int asciiValue= (int)'a' + distance;
            return Character.toChars(asciiValue)[0];
        }

    }
}
