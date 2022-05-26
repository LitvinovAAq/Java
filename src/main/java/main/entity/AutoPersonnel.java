package main.entity;


import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "auto_personnel")
@Data
public class AutoPersonnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, max = 30, message = "Error: This field must be filled in or name name too long")
    private String firstName;


    @Size(min = 2, max = 30, message = "Error: This field must be filled in or mark last name too long")
    private String lastName;

    @Size(max = 30, message = "Error: Father name too long")
    @Column(name = "pather_name")
    private String fatherName;

    public AutoPersonnel() {
    }
}
