package TaskManagementApi.Controllers;
import TaskManagementApi.DTO.UserDTO;
import TaskManagementApi.Services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService US;
    @GetMapping("/all")
    public List<UserDTO> FindAll(){
        return US.getAll();
    }
    @PostMapping("/register")
    public ResponseEntity<?>CreateUser(@Valid@RequestBody UserDTO User, BindingResult result){
        return US.CreateUser(User,result);
    }
    @GetMapping("/{name}")
    private ResponseEntity<?>FindByName(@RequestParam("name") String name){
        return US.searchByName(name);
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<?>DeleteById(@PathVariable Long id){
        return US.DeleteById(id);
    }
    @PutMapping("/Update")
    public ResponseEntity<?>Update(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO
    ,BindingResult result){
        return US.UpdateUser(id,userDTO,result);
    }
}
