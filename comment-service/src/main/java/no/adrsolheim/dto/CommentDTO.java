package no.adrsolheim.dto;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommentDTO {
    // TODO: add validation tests
    private Long id;
    @NotNull
    @PositiveOrZero
    private Long blogId;
    @NotBlank(message = "Comment cannot be empty")
    private String content;
    // TODO: date should be generated
    private String date;
}
