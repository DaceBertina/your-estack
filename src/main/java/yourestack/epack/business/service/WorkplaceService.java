package yourestack.epack.business.service;

import yourestack.epack.business.domain.WorkplaceDTO;

import java.util.List;

public interface WorkplaceService {

    List<WorkplaceDTO> findAll();

    WorkplaceDTO get(Long workplaceId);

    WorkplaceDTO create(WorkplaceDTO workplaceDTO);

    WorkplaceDTO update(Long workplaceId, WorkplaceDTO workplaceDTO);

    void delete(Long workplaceId);
}
