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

public class CreateWeather {

    private static final String _FILE = "data/weather.csv";

    public static List<Triple> getWeather(boolean verbose){

        List<Triple> triples = new ArrayList<>();

        List<String> lines = CSVReader.read(_FILE);

        List<String> headers = new ArrayList<String>(Arrays.asList(lines.get(0).split(",")));

        for(int h=0; h<headers.size();h++){

            String p = headers.get(h).replaceAll("^\"|\"$", "");

            if(verbose) System.out.println(p);

            for(String line:lines){
                Triple t = new Triple();

                Resource subject = new Resource();
                subject.setLabel(line.split(",")[0].replaceAll("^\"|\"$", ""));
                t.setSubject(subject);

                Resource predicate = new Resource();
                predicate.setLabel(p);
                t.setPredicate(predicate);

                Resource object = new Resource();
                object.setLabel(line.split(",")[h].replaceAll("^\"|\"$", ""));
                t.setObject(object);

                if(verbose) System.out.println(t.getObject().getLabel() +"||" + t.getPredicate().getLabel() + "||" + t.getSubject().getLabel());

                Indexer.index(t);

                Triple ta = new Triple();
                ta.setSubject(subject);

                Resource pa = new Resource();
                pa.setLabel(RDFSchemaConstants.SUBCLASS_OF.toString());
                ta.setPredicate(pa);

                Resource sa = new Resource();
                sa.setLabel(ModelConstants.ASPECT.toString());
                ta.setObject(sa);

                if(verbose) System.out.println(ta.getObject().getLabel() +"||" + ta.getPredicate().getLabel() + "||" + ta.getSubject().getLabel());

                Indexer.index(ta);

            }

        }

        return triples;
    }
}
