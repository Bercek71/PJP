package grammarOperation;

import grammar.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class GrammarOps {

    public GrammarOps(Grammar g) {
        this.g = g;
        compute_empty();
        compute_first_table();
        compute_follow_table();
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
                        if (s instanceof Terminal && ((Terminal) s).getType() == Terminal.Type.EPSILON) {
                            continue;
                        }
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

    public Set<Terminal> getFirst(Collection<Symbol> symbols) {
        Set<Terminal> result = new TreeSet<Terminal>();
        int runCount = 0;
        for (Symbol s : symbols) {
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
        if (runCount == symbols.size()) {
            result.add(new Terminal(null));
        }
        return result;
    }

//    public Map<Nonterminal, Set<Terminal>> getFirst() {
//        return firstSets;
//    }

    private void compute_first_table() {
        firstTable = new Table(g.getNonterminals(), g.getSymbols().stream().filter(s -> {
            return !(s instanceof Terminal) || ((Terminal) s).getType() != Terminal.Type.EPSILON;
        }).toList()
        );
        for (Rule r : g.getRules()) {
            for (Symbol s : r.getRHS()) {
                if (s instanceof Terminal && ((Terminal) s).getType() == Terminal.Type.EPSILON) {
                    continue;
                }
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
        firstTable.addRowsTogether();
    }

    private Table followTable;

    private void compute_follow_table() {
        followTable = new Table(g.getNonterminals(), g.getSymbols());


        Terminal epsilon;
        for (Symbol s : g.getSymbols()) {
            if (s instanceof Terminal && ((Terminal) s).getType() == Terminal.Type.EPSILON) {
                followTable.set(g.getStartNonterminal(), s, true);
                epsilon = (Terminal) s;
                break;
            }
        }

        for (Rule r : g.getRules()) {
            for (Symbol s : r.getRHS()) {
                if (s instanceof Nonterminal) {
                    Collection<Symbol> alpha = r.getRHS().subList(r.getRHS().indexOf(s) + 1, r.getRHS().size());
                    if (alpha.isEmpty()) {
                        followTable.set((Nonterminal) s, r.getLHS(), true);
                        continue;
                    }
                    Set<Terminal> first = getFirst(alpha);
                    //followTable.set((Nonterminal) s, t, true);
                    for (Terminal t : first) {
                        if (t.getType() != Terminal.Type.EPSILON) {
                            followTable.set((Nonterminal) s, t, true);
                        }
                        if (t.getType() == Terminal.Type.EPSILON) {
//                            followTable.set((Nonterminal) s, t, true);
                            followTable.set((Nonterminal) s, r.getLHS(), true);
                        }
                    }
                }
            }
        }
        followTable.addRowsTogether();

//        System.out.println(followTable);

    }


    public Set<Terminal> getFollow(Nonterminal nonterminal) {
        return followTable.getTerminals(nonterminal);

    }

    Grammar g;

    Set<Nonterminal> emptyNonterminals;
}
