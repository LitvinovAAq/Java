package main.entity;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Table(name = "journal")
@Data
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private Timestamp time_out;

    private Timestamp time_in;

    @OneToOne()
    @NotNull(message = "Error: This field must be filled in")
    private Auto auto;

    @OneToOne
    @NotNull(message = "Error: This field must be filled in")
    private Routes routes;
    public Journal() {
    }
}
