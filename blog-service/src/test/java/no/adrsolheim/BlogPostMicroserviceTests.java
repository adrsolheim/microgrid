package no.adrsolheim;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.adrsolheim.dto.BlogPostDTO;
import no.adrsolheim.repository.BlogPostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT

)
@Testcontainers
@AutoConfigureMockMvc
public class BlogPostMicroserviceTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BlogPostRepository blogPostRepository;

    @Container
    static MariaDBContainer mariaDBContainer = new MariaDBContainer("mariadb:10.3");
    @DynamicPropertySource
    static void setMariaDBContainerProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        mariaDBContainer.start();
        for(int i = 0; i < 100; i++) {
            System.out.println(mariaDBContainer.getJdbcUrl());
        }
        dynamicPropertyRegistry.add("spring.datasource.url", mariaDBContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", mariaDBContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", mariaDBContainer::getPassword);
    }

    @Test
    public void shouldAddBlogPost() throws Exception {
        BlogPostDTO blogPostDTO = getBlogPostDTO();
        String blogPostJson = objectMapper.writeValueAsString(blogPostDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/blogpost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(blogPostJson))
                .andExpect(status().isCreated());
        Assertions.assertEquals(1, blogPostRepository.findAll().size());
    }

    private BlogPostDTO getBlogPostDTO() {
        return BlogPostDTO.builder()
                .title("Test Title")
                .content("Lorem ipsum")
                .build();
    }
}
