package com.example.demo.models;

public class TrackSearch {
    private String trackName;
    private String artistName;
    private String albumName;
    private String genreName;

    public TrackSearch(String trackName, String artistName, String albumName, String genreName) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.genreName = genreName;
    }


    public String getTrackName() {
        return trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getGenreName() {
        return genreName;
    }
}
