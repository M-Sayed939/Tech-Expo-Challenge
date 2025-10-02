package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UpdateBookRequest {
    @NotBlank(message = "title is required")
    private String title;

    private String author;
    private Integer year;
    private String description;

}

