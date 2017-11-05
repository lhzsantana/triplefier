package br.ufsc.rendezvous.triplefier;

import br.ufsc.rendezvous.Resource;
import br.ufsc.rendezvous.Triple;
import br.ufsc.rendezvous.indexing.Indexer;
import br.ufsc.rendezvous.model.ModelConstants;
import br.ufsc.rendezvous.model.RDFSchemaConstants;
import br.ufsc.rendezvous.utils.CSVReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreatePoints {

    private static final String _FILE = "data/stm.csv";

    public static List<Triple> getPoints(boolean verbose){

        List<Triple> triples = new ArrayList<>();

        List<String> lines = CSVReader.read(_FILE);

        for(String line:lines){
            Triple t = new Triple();

            Resource subject = new Resource();
            subject.setLabel(line.split(",")[0].replaceAll("^\"|\"$", ""));
            t.setSubject(subject);

            Resource predicate = new Resource();
            predicate.setLabel(RDFSchemaConstants.SUBCLASS_OF.toString());
            t.setPredicate(predicate);

            Resource object = new Resource();
            object.setLabel(ModelConstants.POINT.toString());
            t.setObject(object);

            Indexer.index(t);
            if(verbose) System.out.println(t.getSubject().getLabel() +"||" + t.getPredicate().getLabel() + "||" + t.getObject().getLabel());

            indexTime(verbose, subject, line);
            indexPosition(verbose, subject, line);
        }

        return triples;
    }

    private static void indexTime(boolean verbose, Resource subject, String line){

        Triple t = new Triple();

        t.setSubject(subject);

        Resource pt = new Resource();
        pt.setLabel(ModelConstants.TIME.toString());
        t.setPredicate(pt);

        Resource st = new Resource();
        st.setLabel(line.split(",")[1].replaceAll("^\"|\"$", ""));
        t.setObject(st);

        Indexer.index(t);
        if(verbose) System.out.println(t.getSubject().getLabel() +"||" + t.getPredicate().getLabel() + "||" + t.getObject().getLabel());
    }

    private static void indexPosition(boolean verbose, Resource subject, String line){

        Triple t = new Triple();

        t.setSubject(subject);

        Resource pt = new Resource();
        pt.setLabel(ModelConstants.POSITION.toString());
        t.setPredicate(pt);

        Resource st = new Resource();
        st.setLabel(line.split(",")[2].replaceAll("^\"|\"$", ""));
        t.setObject(st);

        Indexer.index(t);
        if(verbose) System.out.println(t.getSubject().getLabel() +"||" + t.getPredicate().getLabel() + "||" + t.getObject().getLabel());
    }
}
