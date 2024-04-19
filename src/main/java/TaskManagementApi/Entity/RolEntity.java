package TaskManagementApi.Entity;

import TaskManagementApi.Enums.RolEnum;
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
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "Rol")
    @Enumerated(EnumType.STRING)
    private RolEnum Rol;
    @OneToOne(targetEntity = UserEntity.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "UsersRol")
    private Set<UserEntity> UsersRol = new HashSet<>();

}
