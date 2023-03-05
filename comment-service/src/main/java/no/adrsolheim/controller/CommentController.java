package no.adrsolheim.controller;

import no.adrsolheim.dto.CommentDTO;
import no.adrsolheim.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public List<CommentDTO> getComments(@PathVariable("id") Long blogId) {
        return commentService.getBlogComments(blogId);
    }

    @GetMapping
    public List<CommentDTO> getAllComments() {
        return commentService.getAllBlogComments();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addComment(@RequestBody CommentDTO commentDTO) {
        boolean added = commentService.addComment(commentDTO);
        if (added) {
            return "Comment successfully posted!";
        }
        return "Failed to post comment";
    }

}
