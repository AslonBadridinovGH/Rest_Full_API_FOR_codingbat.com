package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;

    @Column(nullable = false)
    private String   name;

    private String   text;

    private String   solution;

    private String   hint;

    private String   method;

    private boolean  hasStar;

    @ManyToOne
    private Language language;

}
