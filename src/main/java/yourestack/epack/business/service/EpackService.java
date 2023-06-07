package yourestack.epack.business.service;

import yourestack.epack.business.domain.EpackDTO;

import java.util.List;

public interface EpackService {

    EpackDTO createEpack(EpackDTO epack);

    EpackDTO findEpackById(Long epackId);

    public List<EpackDTO> findAll();

}
