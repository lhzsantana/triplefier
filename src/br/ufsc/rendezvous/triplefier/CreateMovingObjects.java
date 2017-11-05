package br.ufsc.rendezvous.triplefier;

import br.ufsc.rendezvous.Resource;
import br.ufsc.rendezvous.Triple;
import br.ufsc.rendezvous.indexing.Indexer;
import br.ufsc.rendezvous.model.ModelConstants;
import br.ufsc.rendezvous.model.RDFSchemaConstants;
import br.ufsc.rendezvous.utils.CSVReader;

import java.util.ArrayList;
import java.util.List;

public class CreateMovingObjects {

    private static final String MO_FILE = "data/mo.csv";

    public static List<Triple> getMovingObjects(boolean verbose){

        List<Triple> triples = new ArrayList<>();

        List<String> lines = CSVReader.read(MO_FILE);

        for(String line:lines){
            Triple t = new Triple();

            Resource subject = new Resource();
            subject.setLabel(line.split(",")[0].replaceAll("^\"|\"$", ""));
            t.setSubject(subject);

            Resource predicate = new Resource();
            predicate.setLabel(RDFSchemaConstants.SUBCLASS_OF.toString());
            t.setPredicate(predicate);

            Resource object = new Resource();
            object.setLabel(ModelConstants.MOVING_OBJECT.toString());
            t.setObject(object);

            Indexer.index(t);
            if(verbose) System.out.println(t.getSubject().getLabel() +"||" + t.getPredicate().getLabel() + "||" + t.getObject().getLabel());
        }

        return triples;
    }
}
