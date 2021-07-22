package com.example.demo.repository;

import com.example.demo.models.*;

import java.sql.*;
import java.util.ArrayList;

public class TFrepository {

    public ArrayList<Artist> getRandomArtists() {

        ArrayList<Artist> artistArrayList= new ArrayList<>();
        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("select *\n" +
                    "from Customer\n" +
                    "order by RANDOM()\n" +
                    "limit 5");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Artist artist= new Artist();
                artist.setName(rs.getString("FirstName"));
                artistArrayList.add(artist);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return artistArrayList;
    }


    public ArrayList<Track> getRandomTrack() {

        ArrayList<Track> trackArrayList= new ArrayList<>();
        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("select *\n" +
                    "from Track\n" +
                    "order by RANDOM()\n" +
                    "limit 5");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Track track= new Track();
                track.setName(rs.getString("Name"));
                trackArrayList.add(track);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return trackArrayList;
    }
    public ArrayList<Genre> getRandomGenre() {

        ArrayList<Genre> genreArrayList= new ArrayList<>();
        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("select *\n" +
                    "from Genre\n" +
                    "order by RANDOM()\n" +
                    "limit 5");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Genre genre= new Genre();
                genre.setName(rs.getString("Name"));
                genreArrayList.add(genre);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return genreArrayList;
    }
    public ArrayList<Album> getRandomAlbum() {

        ArrayList<Album> albumArrayList= new ArrayList<>();
        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("select *\n" +
                    "from Album\n" +
                    "order by RANDOM()\n" +
                    "limit 5");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Album album= new Album();
                album.setName(rs.getString("Title"));
                albumArrayList.add(album);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return albumArrayList;
    }
    public ArrayList<TrackSearch> getTrackInfo(String trackSearch) {

        ArrayList<TrackSearch> arrayList= new ArrayList<>();

        String URL = "jdbc:sqlite::resource:Chinook_Sqlite (1).sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Track.Name, Artist.Name,Album.Title, Genre.Name\n" +
                    "from Track,Artist,Genre,Album\n" +
                    "where Artist.ArtistId=Album.ArtistId\n" +
                    "and Album.AlbumId=Track.AlbumId\n" +
                    "and Track.GenreId=Genre.GenreId\n" +
                    "and Track.Name like ?;");
            preparedStatement.setString(1,"%" + trackSearch + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                arrayList.add(new TrackSearch(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }


}