package grammarOperation;

import grammar.*;

import java.util.*;

public class GrammarOps {

    public GrammarOps(Grammar g) {
        this.g = g;
        compute_empty();
        compute_first();
    }

    public Set<Nonterminal> getEmptyNonterminals() {
        return emptyNonterminals;
    }

    private void compute_empty() {
        emptyNonterminals = new TreeSet<Nonterminal>();
        boolean changed;
        do {
            changed = false;
            for (Nonterminal nt : g.getNonterminals()) {
                if (emptyNonterminals.contains(nt)) {
                    continue;
                }
                for (Rule r : nt.getRules()) {
                    boolean allEmpty = true;
                    for (Symbol s : r.getRHS()) {
                        if (s instanceof Terminal) {
                            allEmpty = false;
                            break;
                        }
                        if (!emptyNonterminals.contains(s)) {
                            allEmpty = false;
                            break;
                        }
                    }
                    if (allEmpty) {
                        emptyNonterminals.add(nt);
                        changed = true;
                        break;
                    }
                }
            }
        } while (changed);
    }

    private Map<Nonterminal, Set<Terminal>> firstSets;

    public Map<Nonterminal, Set<Terminal>> getFirst() {
        return firstSets;
    }

    private void compute_first() {
        firstSets = new HashMap<Nonterminal, Set<Terminal>>();
        for (Nonterminal nt : g.getNonterminals()) {
            firstSets.put(nt, new TreeSet<Terminal>());
        }
        boolean changed;
        do {
            changed = false;
            for (Nonterminal nt : g.getNonterminals()) {
                for (Rule r : nt.getRules()) {
                    for (Symbol s : r.getRHS()) {
                        if (s instanceof Terminal) {
                            if (!firstSets.get(nt).contains(s)) {
                                firstSets.get(nt).add((Terminal) s);
                                changed = true;
                            }
                            break;
                        }
                        if (s instanceof Nonterminal) {
                            for (Terminal t : firstSets.get(s)) {
                                if (!firstSets.get(nt).contains(t)) {
                                    firstSets.get(nt).add(t);
                                    changed = true;
                                }
                            }
                            if (!emptyNonterminals.contains(s)) {
                                break;
                            }
                        }
                    }
                }
            }
        } while (changed);
    }


    public Nonterminal getFollow() {
        return null;
    }

    Grammar g;

    Set<Nonterminal> emptyNonterminals;
}
