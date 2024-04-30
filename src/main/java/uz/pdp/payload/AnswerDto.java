package uz.pdp.payload;
import lombok.Data;

@Data
public class AnswerDto {

   private String  text;
   private Integer taskId;
   private Integer userId;
   private boolean is_correct;

}
