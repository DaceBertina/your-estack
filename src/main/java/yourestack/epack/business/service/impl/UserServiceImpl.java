package yourestack.epack.business.service.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import yourestack.epack.business.domain.RoleDTO;
import yourestack.epack.business.domain.UserDTO;
import yourestack.epack.business.mappers.RoleMapStruct;
import yourestack.epack.business.mappers.UserMapStruct;
import yourestack.epack.business.model.RoleEntity;
import yourestack.epack.business.model.UserEntity;
import yourestack.epack.business.model.repos.RoleRepository;
import yourestack.epack.business.model.repos.UserRepository;
import yourestack.epack.business.service.UserService;
import yourestack.epack.util.NotFoundException;
import yourestack.epack.util.WebUtil;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapStruct userMapper;

    private final RoleMapStruct roleMapper;

    private final RoleRepository roleRepository;

    @Override
    public UserDTO registerNewUser(UserDTO user) {

        Optional<RoleEntity> role = roleRepository.findByName("user");
        if (role.isPresent()) {
            RoleDTO roleDTO = roleMapper.roleEntityToRoleDto(role.get());
            Set<RoleDTO> roleList = new HashSet<>();
            user.setRoleList(roleList);
            user.roleList.add(roleDTO);
        }
        user.setDateCreated(LocalDateTime.now());
        UserEntity userEntity = userRepository.save(userMapper.userDtoToUserEntity(user));
        log.info("New user registered: {}", userEntity);

        return userMapper.userEntityToUserDto(userEntity);
    }

    @Override
    public UserDTO findByEmail(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return userMapper.userEntityToUserDto(user.get());
        } else {
            log.error("Exception {} is thrown. User with email " + email + " does not exist.", HttpStatus.CONFLICT);
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "User with that email does not exist.");
        }
    }

    public List<UserDTO> findAll() {
        final List<UserEntity> userEntities = userRepository.findAll(Sort.by("userId"));
        return userEntities.stream()
                .map(userMapper::userEntityToUserDto)
                .collect(Collectors.toList());
    }

    public UserDTO get(final Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::userEntityToUserDto)
                .orElseThrow(NotFoundException::new);
    }

    public Long createUserId(final UserDTO userDTO) {
        final UserEntity userEntity = new UserEntity();
        userMapper.userDtoToUserEntity(userDTO);
        return userRepository.save(userEntity).getUserId();
    }

    @Override
    public UserDTO update(Long userId, final UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);
        log.info("User {} to be updated.", userEntity.getUserId());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userEntity.getPassword());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setGender(userDTO.getGender());
        userEntity.setDateOfBirth(userDTO.getDateOfBirth());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        userEntity.setOccupation(userDTO.getOccupation());
        userEntity.setDateCreated(userDTO.getDateCreated());
        userEntity.setLastUpdated(LocalDateTime.now());
        UserEntity updatedUser = userRepository.save(userEntity);
        log.info("User {} has been updated.", updatedUser);

        return userMapper.userEntityToUserDto(userEntity);
    }

    @Override
    public void changePassword(Long userId, final UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);
        log.info("User {} to be updated.", userEntity.getUserId());
        userEntity.setEmail(userEntity.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setUsername(userEntity.getUsername());
        userEntity.setFirstName(userEntity.getFirstName());
        userEntity.setLastName(userEntity.getLastName());
        userEntity.setGender(userEntity.getGender());
        userEntity.setDateOfBirth(userEntity.getDateOfBirth());
        userEntity.setPhoneNumber(userEntity.getPhoneNumber());
        userEntity.setOccupation(userEntity.getOccupation());
        userEntity.setDateCreated(userEntity.getDateCreated());
        UserEntity updatedUser = userRepository.save(userEntity);
        log.info("Password for user {} has been changed.", updatedUser);
    }

    @Override
    public void delete(final Long userId) {
        userRepository.deleteById(userId);
    }


    @Transactional
    public String getReferencedWarning(final Long userId) {
        final UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);
        if (!userEntity.getUserAddress().isEmpty()) {
            return WebUtil.getMessage("client.address.manyToOne.referenced", userEntity.getUserAddress().iterator().next().getAddressId());
        } else if (!userEntity.getUserWorkplaces().isEmpty()) {
            return WebUtil.getMessage("client.workplace.manyToOne.referenced", userEntity.getUserWorkplaces().iterator().next().getWorkplaceId());
        }
        return null;
    }

    public boolean userExists(String username) {
        return userRepository.findByEmail(username).isPresent();
    }

    public boolean clientExists(String username) {
        return userRepository.findAll().stream()
                .anyMatch(user -> user.getEmail().equals(username));
    }
}
