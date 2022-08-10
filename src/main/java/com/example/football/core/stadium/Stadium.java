package com.example.football.core.stadium;

import com.example.football.core.tournament.Tournament;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "fc_stadium")
public class Stadium {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "fc_stadium_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "fc_stadium_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fc_stadium_id_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private long capacity;

    @NotEmpty
    @ManyToMany
    @JoinTable(name = "fc_stadium_tournament",
            joinColumns = {@JoinColumn(name = "id_stadium")},
            inverseJoinColumns = {@JoinColumn(name = "id_tournament")})
    private List<Tournament> accreditationList;

    public List<Tournament> getAccreditationList() {
        return accreditationList;
    }

    public void setAccreditationList(List<Tournament> accreditationList) {
        this.accreditationList = accreditationList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }
}
