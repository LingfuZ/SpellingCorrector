package spell;

/**
 * Created by Lingfu on 5/6/2014.
 */
public class Words implements Trie {

    // Fields

    @Override
    public void add(String word) {

    }

    @Override
    public Node find(String word) {
        return null;
    }

    @Override
    public int getWordCount() {
        return 0;
    }

    @Override
    public int getNodeCount() {
        return 0;
    }

    public class WordNode implements Trie.Node {

        @Override
        public int getValue() {
            return 0;
        }
    }
}
