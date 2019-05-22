package com.example.starwarsapp;

class FilmesClass {
    private String title;
    private String episode_id;
    private String director;
    private String release_date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEpisode_id() {
        return episode_id;
    }

    public void setEpisode_id(String episode_id) {
        this.episode_id = episode_id;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return "Título: "+getTitle()
                +"\nEpisódio: "+getEpisode_id()
                +"\nDiretor: "+getDirector()
                +"\nLançamento: "+getRelease_date();
    }
}
