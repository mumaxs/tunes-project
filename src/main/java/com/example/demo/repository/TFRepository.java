package com.example.demo.repository;

import com.example.demo.models.*;

import java.sql.*;
import java.util.ArrayList;

public class TFRepository {
    private String URL= ConnectionHelper.DATABASE_CONNECTION_URL;
    private Connection conn=null;

    /**
     *
     * @return five random artists from the artist table.
     */
    public ArrayList<Artist> getRandomArtists() {

        ArrayList<Artist> artistArrayList= new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("select *\n" +
                    "from Artist\n" +
                    "order by RANDOM()\n" +
                    "limit 5");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Artist artist= new Artist();
                artist.setName(rs.getString("Name"));
                artistArrayList.add(artist);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return artistArrayList;
    }

    /**
     *
     * @return five random songs from the track table.
     */
    public ArrayList<Track> getRandomTrack() {

        ArrayList<Track> trackArrayList= new ArrayList<>();
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
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return trackArrayList;
    }

    /**
     *
     * @return five random genres from the genre table.
     */
    public ArrayList<Genre> getRandomGenre() {

        ArrayList<Genre> genreArrayList= new ArrayList<>();
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
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return genreArrayList;
    }

    /**
     *
     * @return five random albums from the album table.
     */
    public ArrayList<Album> getRandomAlbum() {

        ArrayList<Album> albumArrayList= new ArrayList<>();
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
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return albumArrayList;
    }

    /**
     *
     * @param trackSearch is a string used to search for a specific track in the DB.
     * @return The method returns a trackSearchArrayList object consisted of:
     * 1)the tracks name,
     * 2)the artists name who created the song,
     * 3)the genre of the song,
     * 4)the album of the song
     */
    public ArrayList<TrackSearch> getTrackInfo(String trackSearch) {

        ArrayList<TrackSearch> trackSearchArrayList= new ArrayList<>();

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
                trackSearchArrayList.add(new TrackSearch(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return trackSearchArrayList;
    }


}