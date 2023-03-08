package no.adrsolheim.service;

import no.adrsolheim.dto.BlogPostDTO;
import no.adrsolheim.event.BlogEvent;
import no.adrsolheim.model.BlogPost;
import no.adrsolheim.repository.BlogPostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogRepository;
    @Autowired
    private KafkaTemplate kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(BlogPostService.class);

    public boolean addBlog(BlogPostDTO blogPostDTO) {
        BlogPost blogPost = mapToBlog(blogPostDTO);
        List<BlogPost> existingBlogs = blogRepository.findByTitle(blogPost.getTitle());
        for (BlogPost b : existingBlogs) {
            if(b.getContent().equals(blogPost.getContent())) {
                logger.info("Blog with title {} already exist with the same content", blogPost.getTitle());
                return false;
            }
        }
        BlogPost added = blogRepository.save(blogPost);
        kafkaTemplate.send("blogNotification", new BlogEvent(added.getId(), "CREATE"));
        logger.info("Blog with title {} saved to database", blogPost.getTitle());
        return true;
    }

    public List<BlogPostDTO> getAllBlogs() {
        return blogRepository.findAll().stream().map(this::mapToBlogDTO).toList();
    }

    private BlogPost mapToBlog(BlogPostDTO dto){
        return BlogPost.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }
    private BlogPostDTO mapToBlogDTO(BlogPost blog){
        return BlogPostDTO.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .content(blog.getContent())
                .build();
    }

}
