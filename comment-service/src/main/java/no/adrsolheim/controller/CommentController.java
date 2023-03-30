package no.adrsolheim.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import no.adrsolheim.dto.CommentDTO;
import no.adrsolheim.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public List<CommentDTO> getComments(@PathVariable("id") Long blogId) {
        log.info("Processing GET request for blogId: {}..", blogId);
        return commentService.getBlogComments(blogId);
    }

    @GetMapping
    public List<CommentDTO> getAllComments() {
        log.info("Received GET request to fetch all comments");
        return commentService.getAllBlogComments();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addComment(@RequestBody @Valid CommentDTO commentDTO, Errors errors) {
        if (errors.hasErrors()) {
            log.info("Comment failed to validate: {}", commentDTO);
            return "Failed to post comment";
        }
        boolean added = commentService.addComment(commentDTO);
        if (added) {
            log.info("Comment successfully added to blog with id {}", commentDTO, commentDTO.getBlogId());
            return "Comment successfully posted!";
        }
        log.info("Failed to add comment {} to blog with id {}", commentDTO, commentDTO.getBlogId());
        return "Failed to post comment";
    }

}
