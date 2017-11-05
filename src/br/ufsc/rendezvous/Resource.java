package br.ufsc.rendezvous;

public class Resource {

    private String label;

    private String id;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getId() {

        if(id==null){
            id=label;
        }

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
