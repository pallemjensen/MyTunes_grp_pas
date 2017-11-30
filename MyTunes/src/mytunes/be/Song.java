/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

/**
 *
 * @author pmj
 */
public class Song {
    
    private int id;
    private String title;
    private String genre;
    private String duration;
    private String file;
    private String artist;

 
//    public Song(int id, String title, String genre, String duration, String file, String artist) {
//        this.id = id;
//        this.title = title;
//        this.genre = genre;
//        this.duration = duration;
//        this.file = file;
//        this.artist = artist;
//    }
   
    /**
     * Get the value of artist
     *
     * @return the value of artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Set the value of artist
     *
     * @param artist new value of artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 
    
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return title;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.title = name;
    }
    
    /**
     * Get the value of genre
     *
     * @return the value of genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Set the value of genre
     *
     * @param genre new value of genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    /**
     * Get the value of duration
     *
     * @return the value of duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Set the value of duration
     *
     * @param duration new value of duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    /**
     * Get the value of file
     * 
     * @return the value of file
     */
    public String getFile() {
        return file;
    }

    /**
     * Set the value of file
     *
     * @param file new value of file
     */
    public void setFile(String file) {
        this.file = file;
    }

   
    public String getTitle() {
        return title;
    }

   
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Song{" + "id=" + id + ", title=" + title + ", genre=" + genre + ", duration=" + duration + ", file=" + file + ", artist=" + artist + '}';
    }

    
    
}
