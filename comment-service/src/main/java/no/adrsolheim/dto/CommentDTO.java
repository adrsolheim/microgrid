package no.adrsolheim.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommentDTO {
    private Long id;
    private Long blogId;
    private String content;
    private String date;
}
