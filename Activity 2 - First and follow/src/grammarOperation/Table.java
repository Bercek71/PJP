package grammarOperation;

import grammar.Nonterminal;
import grammar.Symbol;
import grammar.Terminal;

import java.util.*;

public class Table {
    private Map<Nonterminal, Map<Symbol, Boolean>> table;

    public Table(Collection<Nonterminal> nonterminals, Collection<Symbol> symbols) {
        this.table = new HashMap<Nonterminal, Map<Symbol, Boolean>>();
        for (Nonterminal nt : nonterminals) {
            Map<Symbol, Boolean> row = new HashMap<Symbol, Boolean>();
            for (Symbol s : symbols) {
                row.put(s, false);
            }
            table.put(nt, row);
        }
    }

    public void set(Nonterminal nt, Symbol s, boolean value) {
        table.get(nt).put(s, value);
    }

    public boolean get(Nonterminal nt, Symbol s) {
        return table.get(nt).get(s);
    }

    public Set<Symbol> getNonterminalSymbols(Nonterminal nt) {
        Set<Symbol> symbols = new HashSet<Symbol>();
        for (Symbol s : table.get(nt).keySet()) {
            if (table.get(nt).get(s)) {
                symbols.add(s);
            }
        }
        return symbols;
    }

    public Set<Terminal> getTerminals(Nonterminal nt) {
        Set<Terminal> terminals = new HashSet<Terminal>();
        for (Symbol s : table.get(nt).keySet()) {
            if (s instanceof Terminal && table.get(nt).get(s)) {
                terminals.add((Terminal) s);
            }
        }
        return terminals;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  |");
        for (Symbol s : table.get(table.keySet().iterator().next()).keySet()) {
            sb.append(" ").append(s).append(" |");
        }
        sb.append("\n");
        for (Nonterminal nt : table.keySet()) {
//            sb.append(nt).append(" |");
            for (Symbol s : table.get(nt).keySet()) {
                if (s == table.get(nt).keySet().iterator().next()) {
                    sb.append(nt).append(" |");
                }
                sb.append(" ").append(table.get(nt).get(s) ? "*" : " ").append(" |");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
