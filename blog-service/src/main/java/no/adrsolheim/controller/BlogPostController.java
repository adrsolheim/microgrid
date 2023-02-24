package no.adrsolheim.controller;

import no.adrsolheim.dto.BlogPostDTO;
import no.adrsolheim.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/blogpost")
public class BlogPostController {
    @Autowired
    BlogPostService blogService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createBlog(@RequestBody BlogPostDTO blogDTO) {
        boolean successful = blogService.addBlog(blogDTO);
        String response = successful ? "Blog successfully posted!" : "Unable to post blog..";
        return response;
    }

    @GetMapping(produces = "application/json")
    public List<BlogPostDTO> getAllBlogs() {
       return blogService.getAllBlogs();
    }
}
