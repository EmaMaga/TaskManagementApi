package TaskManagementApi.Services;

import TaskManagementApi.DTO.UserDTO;
import TaskManagementApi.Entity.UserEntity;
import TaskManagementApi.Repository.IUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    IUserRepository UserRepo;
    public ResponseEntity<?>CreateUser(@Valid UserDTO userDTO, BindingResult result){
    if(result.hasErrors()){
        List<String>errorMessage = result.getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errorMessage);
    }
    if (UserRepo.EmailExists(userDTO.getEmail())){
        return ResponseEntity.badRequest().body("Email exists");
    }
    String passwordEncript = new BCryptPasswordEncoder().encode(userDTO.getPassword());
    UserRepo.save(UserEntity.builder()
            .Name(userDTO.getName())
            .Lastname(userDTO.getLastname())
            .UserName(userDTO.getUserName())
            .Email(userDTO.getEmail())
            .Password(passwordEncript)
            .UserRol(userDTO.getUserRol())
            .build()
    );
    return ResponseEntity.ok("User created");
    }

    public List<UserDTO>getAll(){
        List<UserDTO>Lista=UserRepo.findAll().stream()
                .map(User->UserDTO.builder()
                        .UserName(User.getName())
                        .Email(User.getEmail())
                        .build()
                ).toList();
        if (Lista.isEmpty()){
            ResponseEntity.badRequest().body("Users not found");
        }
        return Lista;
    }

    public ResponseEntity<?>searchByName(String Name){
        try {
            Optional<UserEntity> users = UserRepo.FindByName(Name);
            if (users.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dosnt exists");
            }
            List<UserDTO> dto = users.stream().map(
                    user-> UserDTO.builder()
                            .UserName(user.getName())
                            .Name(user.getName())
                            .Lastname(user.getLastname())
                            .Email(user.getEmail())
                            .build()).collect(Collectors.toList());
            return ResponseEntity.ok(dto);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Error trying to find the user\{e.getMessage()}");
        }
    }
    public ResponseEntity<?>UpdateUser(Long Id,@Valid UserDTO user,BindingResult result){
        if (result.hasErrors()){
            List<String>errorMessage = result.getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessage);
        }
        Optional<UserEntity>User = UserRepo.findById(Id);
        if (!User.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User dont exists");
        }
        UserEntity existingUser = User.get();
        existingUser.setName(user.getName());
        existingUser.setLastname(user.getLastname());
        existingUser.setUserName(user.getUserName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setUserRol(user.getUserRol());
        UserRepo.save(existingUser);
        return ResponseEntity.ok("User Updated");
    }
    public ResponseEntity<?>DeleteById(Long id){
        if(UserRepo.existsById(id)){
            UserRepo.deleteById(id);
            return ResponseEntity.ok("User deleted");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
