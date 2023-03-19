package yourestack.epack.business.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yourestack.epack.business.domain.WorkplaceDTO;
import yourestack.epack.business.mappers.WorkplaceMapStruct;
import yourestack.epack.business.model.WorkplaceEntity;
import yourestack.epack.business.model.repos.WorkplaceRepository;
import yourestack.epack.business.service.WorkplaceService;
import yourestack.epack.util.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class WorkplaceServiceImpl implements WorkplaceService {

    private final WorkplaceRepository workplaceRepository;
    private final WorkplaceMapStruct workplaceMapper;

    @Override
    public List<WorkplaceDTO> findAll() {
        final List<WorkplaceEntity> workplaces = workplaceRepository.findAll(Sort.by("workplaceId"));
        return workplaces.stream()
                .map(workplaceMapper::workplaceEntityToWorkplaceDto)
                .collect(Collectors.toList());
    }

    @Override
    public WorkplaceDTO get(Long workplaceId) {
        return workplaceRepository.findById(workplaceId)
                .map(workplaceMapper::workplaceEntityToWorkplaceDto)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public WorkplaceDTO create(WorkplaceDTO workplaceDTO) {

        WorkplaceEntity workplaceEntity = workplaceRepository.save(workplaceMapper.workplaceDtoToWorkplaceEntity(workplaceDTO));
        log.info("New workplace added: {}", workplaceEntity);

        return workplaceMapper.workplaceEntityToWorkplaceDto(workplaceEntity);
    }

    @Override

    public WorkplaceDTO update(Long workplaceId, WorkplaceDTO workplaceDTO) {
        final WorkplaceEntity workplaceEntity = workplaceRepository.findById(workplaceId)
                .orElseThrow(NotFoundException::new);
        workplaceMapper.updateEntity(workplaceDTO, workplaceEntity);
        workplaceRepository.save(workplaceEntity);
        return workplaceMapper.workplaceEntityToWorkplaceDto(workplaceEntity);
    }

    @Override
    public void delete(Long workplaceId) {

        workplaceRepository.deleteById(workplaceId);
    }

}
