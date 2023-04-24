package com.example.MovieApp.documents;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("moviedetails")
public class MovieDetails {
    @Id
    private int id;
    private String name;
    private int rating;

    public MovieDetails(int id, String name, int rating){
        super();
        this.id = id;
        this.name = name;
        this.rating = rating;

    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getRating(){
        return rating;
    }
    public void setRating(int rating){
        this.rating = rating;
    }
}
