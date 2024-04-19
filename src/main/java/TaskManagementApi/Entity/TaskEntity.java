package TaskManagementApi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "Name")
    private String Name;
    private Date CreatedAt;
    @OneToOne(targetEntity = StatementEntity.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "Statement")
    private StatementEntity statement;
    @ManyToMany(targetEntity = UserEntity.class)
    @JoinColumn(name = "DelegatedUsers")
    private Set<UserEntity> DelegatedUsers=new HashSet<>();
    @ManyToMany(targetEntity = UserEntity.class)
    @JoinColumn(name = "ReservatedBy")
    private Set<UserEntity>ReservatedBy = new HashSet<>();
}
