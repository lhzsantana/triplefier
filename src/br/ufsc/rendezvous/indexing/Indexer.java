package br.ufsc.rendezvous.indexing;

import br.ufsc.rendezvous.Resource;
import br.ufsc.rendezvous.Triple;

import java.util.HashMap;
import java.util.Map;

public class Indexer {

    private static Map<String, Triple> SPO = new HashMap<String, Triple>();
    private static Map<Resource, Triple> S_PO = new HashMap<Resource, Triple>();
    private static Map<Resource, Triple> P_SO = new HashMap<Resource, Triple>();
    private static Map<Resource, Triple> O_PS = new HashMap<Resource, Triple>();

    public static void index(Triple t) {
        if(t!=null && t.getSubject() != null && t.getObject() != null && t.getPredicate() != null) {

            SPO.put(t.getSubject().getId() + t.getPredicate().getId() + t.getObject().getId(), t);
            S_PO.put(t.getSubject(), t);
            P_SO.put(t.getPredicate(), t);
            O_PS.put(t.getObject(), t);
        }
    }

    public static Resource getSubjectFromSPO(String s) {

        Triple t = SPO.get(s);

        if(t!=null) {

            return t.getSubject();
        }

        return null;
    }

    public static void countTriples() {

        System.out.println(SPO.size());
    }
}
