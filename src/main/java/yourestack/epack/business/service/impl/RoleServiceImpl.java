package yourestack.epack.business.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import yourestack.epack.business.domain.RoleDTO;
import yourestack.epack.business.mappers.RoleMapStruct;
import yourestack.epack.business.model.RoleEntity;
import yourestack.epack.business.model.repos.RoleRepository;
import yourestack.epack.business.service.RoleService;

@Log4j2
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapStruct roleMapper;

    @Override
    public RoleDTO createNewRole(RoleDTO role) {

        RoleEntity roleEntity = roleRepository.save(roleMapper.roleDtoToRoleEntity(role));
        log.info("New client registered: {}", roleEntity);

        return roleMapper.roleEntityToRoleDto(roleEntity);
    }
}
