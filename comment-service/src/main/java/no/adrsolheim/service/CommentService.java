package no.adrsolheim.service;

import no.adrsolheim.dto.CommentDTO;
import no.adrsolheim.repository.CommentRepository;
import no.adrsolheim.model.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    public List<CommentDTO> getBlogComments(Long blogId) {
        List<Comment> fetched = commentRepository.findByBlogId(blogId);
        logger.info("Fetched {} comments from database", fetched.size());
        List<CommentDTO> result = fetched.stream().map(this::mapCommentToDTO).collect(Collectors.toList());
        return result;
    }

    public List<CommentDTO> getAllBlogComments() {
        List<Comment> fetched = (List<Comment>) commentRepository.findAll();
        logger.info("Fetched {} comments from database", fetched.size());
        List<CommentDTO> result = fetched.stream().map(this::mapCommentToDTO).collect(Collectors.toList());
        return result;
    }


    public boolean addComment(CommentDTO commentDTO) {
        Comment comment = mapDTOToComment(commentDTO);
        commentRepository.save(comment);
        logger.info("Comment with id {} saved to database", comment.getId());
        return true;
    }


    private CommentDTO mapCommentToDTO(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .blogId(comment.getBlogId())
                .content(comment.getContent())
                .date(comment.getDate())
                .build();
    }
    private Comment mapDTOToComment(CommentDTO commentDTO) {
        return Comment.builder()
                .id(commentDTO.getId())
                .blogId(commentDTO.getBlogId())
                .content(commentDTO.getContent())
                .date(commentDTO.getDate())
                .build();
    }
}
