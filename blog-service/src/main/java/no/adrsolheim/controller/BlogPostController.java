package no.adrsolheim.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import no.adrsolheim.dto.BlogPostDTO;
import no.adrsolheim.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/blogpost")
public class BlogPostController {
    @Autowired
    BlogPostService blogService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createBlog(@RequestBody @Valid BlogPostDTO blogDTO, Errors errors) {
        if (errors.hasErrors()) {
            log.info("Blog post failed to validate: {}", blogDTO);
            return "Invalid blog post";
        }

        boolean successful = blogService.addBlog(blogDTO);
        if (successful) {
           log.info("Blog post successfully added: {}", blogDTO);
            return "Blog successfully posted!";
        } else {
            log.info("Failed to add blog post: {}", blogDTO);
            return "Failed to add blog post..";
        }
    }

    @GetMapping(produces = "application/json")
    public List<BlogPostDTO> getAllBlogs() {
        log.info("Processing request to return all blog posts");
        return blogService.getAllBlogs();
    }
}
