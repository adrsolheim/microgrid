package no.adrsolheim.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * This class could be split into two if
 * the incoming object from a request differs
 * from the outgoing object in a response.
 */

@AllArgsConstructor
@Builder
@Data
public class BlogPostDTO {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
