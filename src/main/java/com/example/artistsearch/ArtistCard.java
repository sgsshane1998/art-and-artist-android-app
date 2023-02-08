package com.example.artistsearch;

public class ArtistCard {
    private String name;
    private String id;
    private String link;

    public ArtistCard(String name, String id, String link) {
        this.name = name;
        this.id = id;
        this.link = link;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public String getLink() {
        return this.link;
    }
}
