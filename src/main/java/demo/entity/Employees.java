package demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Employees")
@Getter @Setter
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    @Column(name = "last_name")
    private String lastname;
    @NotEmpty
    @Email
    private String email;
    @NotNull
    private String phone;
    @NotEmpty
    private String gender;
    @NotNull
    private Double salary;
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    
    public Employees() {
    	super();
    }

    public Employees(Long id, String name, String lastname, String email, String phone, String gender, Double salary, Date date) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.salary = salary;
        this.date = date;
    }
}
