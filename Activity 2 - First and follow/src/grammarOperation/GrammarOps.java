package grammarOperation;

import grammar.*;

import java.util.*;

public class GrammarOps {

    public GrammarOps(Grammar g) {
        this.g = g;
        compute_empty();
        compute_first_table();
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

    private Table firstTable;

    public Set<Terminal> getFirst(Rule r) {
        Set<Terminal> result = new TreeSet<Terminal>();
        int runCount = 0;
        for (Symbol s : r.getRHS()) {
            if (s instanceof Terminal) {
                result.add((Terminal) s);
                return result;
            }
            if (s instanceof Nonterminal) {
                result.addAll(firstTable.getTerminals((Nonterminal) s));
                if (!emptyNonterminals.contains(s)) {
                    return result;
                }
            }
            runCount++;
        }
        if (runCount == r.getRHS().size()) {
            result.add(new Terminal("{e}"));
        }
        return result;
    }

//    public Map<Nonterminal, Set<Terminal>> getFirst() {
//        return firstSets;
//    }

    private void compute_first_table() {
        firstTable = new Table(g.getNonterminals(), g.getSymbols());
        for (Rule r : g.getRules()) {
            for (Symbol s : r.getRHS()) {
                if (s instanceof Terminal) {
                    firstTable.set(r.getLHS(), s, true);
                    break;
                }
                if (s instanceof Nonterminal) {
                    firstTable.set(r.getLHS(), s, true);
                    if (!emptyNonterminals.contains(s)) {
                        break;
                    }
                }
            }
        }
        boolean changed;
        do {
            changed = false;
            for (Nonterminal nt : g.getNonterminals()) {
                for (Symbol s : g.getSymbols()) {
                    if (s instanceof Nonterminal && firstTable.get(nt, s)) {
                        for (Symbol s1 : g.getSymbols()) {
                            if (firstTable.get((Nonterminal) s, s1)) {
                                if (!firstTable.get(nt, s1)) {
                                    firstTable.set(nt, s1, true);
                                    changed = true;
                                }
                            }

                        }
                    }
                }
            }
        } while (changed);
        System.out.println(firstTable);
    }


    public Nonterminal getFollow() {
        return null;
    }

    Grammar g;

    Set<Nonterminal> emptyNonterminals;
}
