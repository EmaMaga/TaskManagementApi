package TaskManagementApi.Entity;

import TaskManagementApi.Enums.StateEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "States")
    @Enumerated(EnumType.STRING)
    private StateEnum stateEnum;
    @OneToOne(targetEntity = TaskEntity.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "TaskStatement")
    private Set<TaskEntity>TaskStatement = new HashSet<>();
}
