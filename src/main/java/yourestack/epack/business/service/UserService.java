package yourestack.epack.business.service;


import yourestack.epack.business.domain.UserDTO;

public interface UserService {

    UserDTO registerNewUser(UserDTO user);

    UserDTO findByEmail(String email);
}
