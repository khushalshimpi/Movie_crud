package com.example.MovieApp;

import com.example.MovieApp.documents.MovieDetails;
import com.example.MovieApp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@EnableMongoRepositories(basePackages = "com.example.MovieApp.repository")
@ComponentScan(basePackages = "com.example.MovieApp")
@SpringBootApplication
public class MovieAppApplication implements CommandLineRunner {

	@Autowired
	MovieRepository movieRepo;

	public static void main(String[] args) {
		SpringApplication.run(MovieAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("-------------CREATE MOVIE-------------------------------\n");

		createMovie();

		System.out.println("\n----------------SHOW ALL MOVIE ---------------------------\n");

		showAllMovie();

		System.out.println("\n--------------GET MOVIE BY NAME-----------------------------------\n");

		getMovieByName("Vikings");

		System.out.println("\n-----------UPDATE MOVIE NAME ----------------\n");

		updateMovieName("Vikings");

		System.out.println("\n----------DELETE MOVIE----------------------------------\n");

		deleteMovie(3);

		System.out.println("\n------------FINAL COUNT OF MOVIE-------------------------\n");

		findCountOfMovie();

		System.out.println("\n-------------------THANK YOU---------------------------");

	}
	void createMovie() {
		System.out.println("Data creation started...");
		movieRepo.save(new MovieDetails(1, "Avenger", 5));
		movieRepo.save(new MovieDetails(2, "Spider man", 7));
		movieRepo.save(new MovieDetails(3, "Vikings", 9));
		System.out.println("Data creation complete...");
	}
	public void showAllMovie() {

		movieRepo.findAll().forEach(item -> System.out.println(getMovieDetails(item)));
	}

	//  Get Movie by name
	public void getMovieByName(String  name) {
		System.out.println("Getting Movie by name: " + name);
		MovieDetails item = movieRepo.findItemByName(name);
		System.out.println(getMovieDetails(item));
	}

	// Get Movie count of documents in the collection
	public void findCountOfMovie() {
		long count = movieRepo.count();
		System.out.println("Number of documents in the collection: " + count);
	}
	public String getMovieDetails(MovieDetails item) {

		System.out.println(
				 "Movie ID: " + item.getId() +
						", \nMovie Name: " + item.getName() +
						", \nMovie Rating: " + item.getRating()
		);

		return "";
	}
	public void updateMovieName(String name) {

		// Change to this new value
		String newName = "Batman";

		// Find all the items with the category snacks
		List<MovieDetails> list = movieRepo.findAll(name);

		list.forEach(item -> {
			// Update the name in each document
			item.setName(newName);
		});

		// Save all the items in database
		List<MovieDetails> newUp = movieRepo.saveAll(list);

		if(newUp != null)
			System.out.println("Successfully updated " + newUp + " Movie name.");
	}
	// DELETE
	public void deleteMovie(int id) {
		movieRepo.deleteById(id);
		System.out.println("Movie with id " + id + " deleted...");
	}
}