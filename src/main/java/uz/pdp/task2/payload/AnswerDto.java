package uz.pdp.task2.payload;
import lombok.Data;

@Data
public class AnswerDto {

   private String  text;
   private boolean is_correct;
   private Integer taskId;
   private Integer userId;

}
