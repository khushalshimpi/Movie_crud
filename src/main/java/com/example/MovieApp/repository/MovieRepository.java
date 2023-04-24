//package com.example.MovieApp.repository;

//public class MovieRepository {
//}

package com.example.MovieApp.repository;



import com.example.MovieApp.documents.MovieDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<MovieDetails, Integer> {

    @Query("{name:'?0'}")
    MovieDetails findItemByName(String name);

    @Query(value="{name:'?0'}")
    List<MovieDetails> findAll(String name);

    public long count();

}

