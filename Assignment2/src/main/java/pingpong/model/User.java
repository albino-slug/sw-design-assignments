package pingpong.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+$", message = "Invalid Username")
    private String username;

    @Column
    @NotNull
    @Length(min = 4, message = "Password has to be at least 4 characters long")
    @Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$", message = "Password must have at least one digit, one lowercase and one uppercase letter, one special character and mustn't contain any whitespaces")
    private String password;

    @Column
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Incorrect e-mail format")
    private String email;

    @NotNull
    @Min(value = 16, message = "Must be over 16 years of age in order to have an account")
    private Integer age;

    @Column
    @NotNull
    private Integer account;

    @ManyToMany(mappedBy = "players")
    private List<Tournament> tournaments = new ArrayList<Tournament>();

    public User(){}

    public User(@NotNull @Pattern(regexp = "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+$", message = "Invalid Username") String username, @NotNull @Min(value = 16, message = "Must be over 16 years of age in order to have an account") int age, @NotNull @Length(min = 4, message = "Password has to be at least 4 characters long") @Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$", message = "Password must have at least one digit, one lowercase and one uppercase letter, one special character and mustn't contain any whitespaces") String password, @NotNull Role role, @NotNull @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Incorrect e-mail format") String email, @NotNull Integer account) {
        this.age = age;
        this.role = role;
        this.email = email;
        this.password = password;
        this.username = username;
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public void addToAccount(Integer sum){
        this.account += sum;
    }

    public Boolean withdrawFromAccount(Integer sum){
        if (sum > this.account){
            System.out.println("[ERROR] Account doesnt have enough money for the current withdrawal.");
            return Boolean.FALSE;
        }
        this.account -= sum;
        return Boolean.TRUE;
    }

    public void enrollInTournament(Tournament tournament){
        this.getTournaments().add(tournament);
    }

    public void disenrollFromTournament(Tournament tournament){
        this.getTournaments().remove(tournament);
    }

    @Override
    public boolean equals(Object v) {
        if (v == null){
            return false;
        }
        if (!(v instanceof User)) {
            return false;
        }
        return ((User)v).getId().intValue() == this.getId().intValue();
    }

    @Override
    public String toString() {
        return "User{" +
                "role=" + role +
                ", id=" + id +
                ", age=" + age +
                ", email=" + email +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
