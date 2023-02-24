package no.adrsolheim.repository;

import no.adrsolheim.model.BlogPost;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {
    Optional<BlogPost> findById(long id);
    List<BlogPost> findByTitle(String title);
    List<BlogPost> findAll();

}
