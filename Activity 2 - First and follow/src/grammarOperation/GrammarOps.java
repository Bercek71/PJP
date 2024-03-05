package grammarOperation;

import grammar.*;
import java.util.*;

public class GrammarOps {

    public GrammarOps(Grammar g) {
        this.g = g;
        compute_empty();
    }

    public Set<Nonterminal> getEmptyNonterminals() {
        return emptyNonterminals;
    }

    private void compute_empty() {
        emptyNonterminals = new TreeSet<Nonterminal>();
        //TODO: start here
    }

    Grammar g;

    Set<Nonterminal> emptyNonterminals;
}
