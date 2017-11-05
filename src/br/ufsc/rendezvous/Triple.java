package br.ufsc.rendezvous;

import java.util.UUID;

public class Triple {

    private String id;

    private Resource predicate;
    private Resource subject;
    private Resource object;

    public Triple(){
        id= UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Resource getPredicate() {
        return predicate;
    }

    public void setPredicate(Resource predicate) {
        this.predicate = predicate;
    }

    public Resource getSubject() {
        return subject;
    }

    public void setSubject(Resource subject) {
        this.subject = subject;
    }

    public Resource getObject() {
        return object;
    }

    public void setObject(Resource object) {
        this.object = object;
    }
}
