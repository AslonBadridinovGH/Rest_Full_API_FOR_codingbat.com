package uz.pdp.task2.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TaskDto {

    @NotNull(message = "Email bösh bölmasin")
    private String   name;

    private String   text;
    private String   solution;
    private String   hint;
    private String   method;
    private boolean  hasStar;
    private Integer  languageId;
}
