package main.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "routes")
@Data
public class Routes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, max=20, message = "Error: This field must be filled in or route name name too long")
    private String name;

    public Routes() {
    }
}
