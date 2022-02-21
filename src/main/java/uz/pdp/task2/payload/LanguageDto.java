package uz.pdp.task2.payload;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class LanguageDto {

   @NotNull(message = "Language name bösh bölmasin")
   private String name;

   private List<Integer> categoryIds;

}
