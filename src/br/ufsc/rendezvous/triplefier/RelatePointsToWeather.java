package br.ufsc.rendezvous.triplefier;

import br.ufsc.rendezvous.Resource;
import br.ufsc.rendezvous.Triple;
import br.ufsc.rendezvous.indexing.Indexer;
import br.ufsc.rendezvous.model.ModelConstants;
import br.ufsc.rendezvous.model.RDFSchemaConstants;
import br.ufsc.rendezvous.utils.CSVReader;

import java.util.ArrayList;
import java.util.List;

public class RelatePointsToWeather {

    private static final String _FILE = "data/stm_weather.csv";

    public static List<Triple> relatePointsToWeather(boolean verbose){

        List<Triple> triples = new ArrayList<>();

        List<String> lines = CSVReader.read(_FILE);

        for(String line:lines){
            Triple t = new Triple();

            Resource subject = Indexer.getSubjectFromSPO(
                    line.split(",")[0].replaceAll("^\"|\"$", "")+
                    RDFSchemaConstants.SUBCLASS_OF+
                    ModelConstants.POINT);
            t.setSubject(subject);

            Resource predicate = new Resource();
            predicate.setLabel(ModelConstants.HAS_ASPECT.toString());
            t.setPredicate(predicate);

            Resource object = Indexer.getSubjectFromSPO(
                    line.split(",")[1].replaceAll("^\"|\"$", "")+
                            RDFSchemaConstants.SUBCLASS_OF+
                            ModelConstants.ASPECT);
            t.setObject(object);

            if(t!=null && t.getSubject() != null && t.getObject() != null && t.getPredicate() != null) {
                Indexer.index(t);

                if(verbose) System.out.println(t.getSubject().getLabel() +"||" + t.getPredicate().getLabel() + "||" + t.getObject().getLabel());
            }

        }

        return triples;
    }
}
