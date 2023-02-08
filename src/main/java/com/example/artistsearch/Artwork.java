package com.example.artistsearch;

public class Artwork {
    private String artworkId;
    private String title;
    private String link;

    public Artwork(String artworkId, String title, String link) {
        this.artworkId = artworkId;
        this.title = title;
        this.link = link;
    }

    public String getId() {
        return this.artworkId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getLink() {
        return this.link;
    }

}
