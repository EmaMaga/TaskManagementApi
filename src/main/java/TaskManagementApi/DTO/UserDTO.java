package TaskManagementApi.DTO;

import TaskManagementApi.Entity.RolEntity;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    @NotBlank(message = "This field cant e empty")
    private String Name;
    @NotBlank(message = "This field cant e empty")
    private String Lastname;
    @NotBlank(message = "This field cant e empty")
    private String UserName;
    @NotBlank(message = "This field cant e empty")
    @Email(message = "It has to be a valid email")
    private String Email;
    @NotBlank(message = "This field cant e empty")
    @Size(min = 6, max = 15, message = "The password has to be between 8 and 15 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()\\-\\[{}\\]:;',?/*~$^+=<>.]).{8,14}$", message = "Invalid Format")
    private String Password;
    @NotNull
    private RolEntity UserRol;


}
