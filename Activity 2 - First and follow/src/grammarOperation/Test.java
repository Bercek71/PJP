package grammarOperation;

import java.io.*;
import java.util.Collection;

import grammar.*;

public class Test {

    public static void main(String[] args) {
        Grammar grammar;

        try {
            GrammarReader inp = new GrammarReader(new FileReader(args[0]));
            grammar = inp.read();
        } catch (GrammarException e) {
            System.err.println("Error(" + e.getLineNumber() + ") " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        grammar.dump(System.out);

        GrammarOps go = new GrammarOps(grammar);

        /* first step, computing nonterminals that can generate empty word */
//        for (Nonterminal nt : go.getEmptyNonterminals()) {
//            System.out.print(nt.getName() + " ");
//        }
//        System.out.println();

        //First
        for (Rule r : grammar.getRules()) {
            StringBuilder sb = new StringBuilder();
            sb.append("First[").append(r.getLHS().getName()).append(":");
            for (Symbol s : r.getRHS()) {
                sb.append(s.getName());
            }
            sb.append("] = ");
            Collection<Terminal> first = go.getFirst(r.getRHS());
            for (Terminal t : first) {
                sb.append(t.getName()).append(" ");
            }
            sb.append("\n");
            System.out.print(sb.toString());
        }

    }


}
