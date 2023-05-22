package yourestack.epack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yourestack.epack.business.domain.UserDTO;
import yourestack.epack.business.mappers.UserMapStruct;
import yourestack.epack.business.model.UserEntity;
import yourestack.epack.business.model.repos.RoleRepository;
import yourestack.epack.business.model.repos.UserRepository;
import yourestack.epack.business.service.impl.UserServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository repository;

    @Mock
    private UserMapStruct mapper;

    @InjectMocks
    private UserServiceImpl service;

    private UserDTO userDto;

    private UserEntity userEntity;


    @BeforeEach
    public void init() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

        userDto = new UserDTO();
        userDto.setUserId(3L);
        userDto.setFirstName("Peter");
        userDto.setLastName("Pen");
        userDto.setGender("M");
        userDto.setUsername("PeterPen");
        userDto.setEmail("peter@gmail.com");
        userDto.setPassword("PeterPeter");
        userDto.setPasswordConfirmation("PeterPeter");
        userDto.setOldPassword("PeterPeter");
        userDto.setNewPassword("PeterPeter1");
        userDto.setDateOfBirth(LocalDate.parse("1911-12-31T23:59:00", dateTimeFormatter));
        userDto.setPhoneNumber("1234567");
        userDto.setOccupation("friend");
        userDto.setDateCreated(LocalDateTime.parse("2023-02-01T09:00:00", dateTimeFormatter));
        userDto.setLastUpdated(LocalDateTime.parse("2023-02-01T09:00:00", dateTimeFormatter));
        userDto.setRoleList(null);
        userDto.setOrderList(null);

        userEntity = new UserEntity();
        userEntity.setUserId(3L);
        userEntity.setFirstName("Peter");
        userEntity.setLastName("Pen");
        userEntity.setGender("M");
        userEntity.setUsername("PeterPen");
        userEntity.setEmail("peter@gmail.com");
        userEntity.setPassword("PeterPeter");
        userEntity.setDateOfBirth(LocalDate.parse("1911-12-31T23:59:00", dateTimeFormatter));
        userEntity.setPhoneNumber("1234567");
        userDto.setOccupation("friend");
        userEntity.setDateCreated(LocalDateTime.parse("2023-02-01T09:00:00", dateTimeFormatter));
        userEntity.setLastUpdated(LocalDateTime.parse("2023-02-01T09:00:00", dateTimeFormatter));
        userEntity.setRoleEntityList(null);
        userEntity.setOrderEntityList(null);

    }

    @Test
    public void createNewClientTest() {
        when(mapper.userDtoToUserEntity(userDto)).thenReturn(userEntity);
        when(repository.save(userEntity)).thenReturn(userEntity);
        when(mapper.userEntityToUserDto(userEntity)).thenReturn(userDto);

        UserDTO clientToBeRegistered = service.registerNewUser(userDto);

        assertEquals(userDto, clientToBeRegistered);

        verify(repository, times(1)).save(userEntity);
    }

}
