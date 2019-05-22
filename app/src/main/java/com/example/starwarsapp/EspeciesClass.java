package com.example.starwarsapp;

class EspeciesClass {
    private String name;
    private String classification;
    private String avarege_height;
    private String avarege_lifespan;
    private String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getAvarege_height() {
        return avarege_height;
    }

    public void setAvarege_height(String avarege_height) {
        this.avarege_height = avarege_height;
    }

    public String getAvarege_lifespan() {
        return avarege_lifespan;
    }

    public void setAvarege_lifespan(String avarege_lifespan) {
        this.avarege_lifespan = avarege_lifespan;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    @Override
    public String toString() {
        return "Nome: "+getName()
                +"\nClassificação: "+getClassification()
                +"\nPeso aproximado: "+getAvarege_height()
                +"\nExpectativa de vida: "+getAvarege_lifespan()
                +"\nLíngua: "+getLanguage();

    }
}
