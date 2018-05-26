package pingpong.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private TournamentFee tournamentFee;

    @OneToMany(mappedBy="tournament")
    private List<Match> matches = new ArrayList<Match>();

    public Tournament(){}

    public Tournament(Integer id, String name, Date endDate, Date startDate, TournamentFee tournamentFee) {
        this.id = id;
        this.name = name;
        this.endDate = endDate;
        this.startDate = startDate;
        this.tournamentFee = tournamentFee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public TournamentFee getTournamentFee() {
        return tournamentFee;
    }

    public void setTournamentFee(TournamentFee tournamentFee) {
        this.tournamentFee = tournamentFee;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", endDate=" + endDate +
                ", startDate=" + startDate +
                ", tournamentFee=" + tournamentFee +
                '}';
    }
}
