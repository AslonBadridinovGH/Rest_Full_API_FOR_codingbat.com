package uz.pdp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TaskDto {

    @NotNull(message = "Email must be not empty")
    private String   name;

    private String   text;
    private String   solution;
    private String   hint;
    private String   method;
    private boolean  hasStar;
    private Integer  languageId;
}
