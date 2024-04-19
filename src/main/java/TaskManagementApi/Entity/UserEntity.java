package TaskManagementApi.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Lastname")
    private String Lastname;
    @Column(name = "UserName",unique = true,nullable = false)
    private String UserName;
    @Column(name = "Email",unique = true,nullable = false)
    private String Email;
    @Column(name = "Password")
    private String Password;
    @OneToOne(targetEntity = RolEntity.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "UserRol")
    private RolEntity UserRol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(UserRol.getRol().toString()));
    }

    @Override
    public String getUsername() {
        return this.Name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
