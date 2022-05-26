package main.entity;

import lombok.Data;
import main.service.AutoPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "auto")
@Data
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Size(min=6, max=6,message = "Error: This field must be filled in or auto number is not correct")
    private String num;

    @Size(min=2, max=15,message = "Error: This field must be filled in or color name too long")
    private String color;

    @Size(min=2, max=15,message = "Error: This field must be filled in or mark name too long")
    private String mark;

    @OneToOne()
    private AutoPersonnel autoPersonnel;


    public Auto() {
    }

    public Auto(String num, String color, String mark, AutoPersonnel autoPersonnel) {
        this.num = num;
        this.color = color;
        this.mark = mark;
        this.autoPersonnel = autoPersonnel;
    }
}
