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

    @Column
    @NotNull
    private Integer tournamentPrice;

    @OneToMany(mappedBy="tournament")
    private List<Match> matches = new ArrayList<Match>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Tournament_User",
            joinColumns = { @JoinColumn(name = "tournament_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    List<User> players  = new ArrayList<User>();

    private static final Integer maxPlayers = 8;

    public Tournament(){}

    public Tournament(Integer id, String name, Date endDate, Date startDate, TournamentFee tournamentFee, Integer tournamentPrice) {
        this.id = id;
        this.name = name;
        this.endDate = endDate;
        this.startDate = startDate;
        this.tournamentFee = tournamentFee;
        this.tournamentPrice = tournamentPrice;
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

    public Integer getTournamentPrice() {
        return tournamentPrice;
    }

    public void setTournamentPrice(Integer tournamentPrice) {
        this.tournamentPrice = tournamentPrice;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public TournamentCategory getTournamentCategory(){
        Date currentDate = new Date();
        if (currentDate.compareTo(this.startDate) >= 0 && currentDate.compareTo(this.endDate) < 0){
            return TournamentCategory.ONGOING;
        }
        else if (currentDate.compareTo(this.startDate) < 0 && currentDate.compareTo(this.endDate) < 0){
            return TournamentCategory.UPCOMING;
        }
        else if (currentDate.compareTo(this.startDate) > 0 && currentDate.compareTo(this.endDate) > 0){
            return TournamentCategory.FINISHED;
        }
        System.out.println("[ERROR] in processing tournament time category");
        return TournamentCategory.FINISHED;
    }

    public Boolean addNewPlayer(User player){
        if (players.size() == maxPlayers){
            System.out.println("[ERROR] could not enroll in tournament since tournament " + this.getName() + " is full.");
            return Boolean.FALSE;
        }
        else if(players.contains(player)){
            System.out.println("[ERROR] player already enrolled in tournament.");
            return Boolean.FALSE;
        }
        players.add(player);
        return Boolean.TRUE;
    }

    public void removePlayer(User player){
        if (players.contains(player)) {
            players.remove(player);
        }
        else{
            System.out.println("[ERROR] player isn't enrolled in tournament.");
        }
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", endDate=" + endDate +
                ", startDate=" + startDate +
                ", tournamentFee=" + tournamentFee +
                ", tournamentPrice=" + tournamentPrice +
                '}';
    }
}
