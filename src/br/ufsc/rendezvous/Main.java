package br.ufsc.rendezvous;

import br.ufsc.rendezvous.indexing.Indexer;
import br.ufsc.rendezvous.triplefier.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        CreateVenues.getVenues(false);
        CreateWeather.getWeather(false);
        CreateMovingObjects.getMovingObjects(false);
        CreatePoints.getPoints(false);
        RelateMovingObjectsToPoints.relateMovingObjectsToPoints(false);
        RelatePointsToWeather.relatePointsToWeather(false);
        RelatePointsToVenue.relatePointsToVenue(false);

        Indexer.countTriples();
    }

}
