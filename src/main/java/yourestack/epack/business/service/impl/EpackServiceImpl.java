package yourestack.epack.business.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import yourestack.epack.business.domain.EpackDTO;
import yourestack.epack.business.mappers.EpackMapStruct;
import yourestack.epack.business.model.EpackEntity;
import yourestack.epack.business.model.repos.EpackRepository;
import yourestack.epack.business.service.EpackService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class EpackServiceImpl implements EpackService {

    private final EpackRepository epackRepository;

    private final EpackMapStruct epackMapper;

    @Override
    public EpackDTO createEpack(EpackDTO epack) {

        EpackEntity epackEntity = epackRepository.save(epackMapper.epackDtoToEpackEntity(epack));
        log.info("New course added: {}", epack);

        return epackMapper.epackEntityToEpackDto(epackEntity);
    }

    @Override
    public EpackDTO findEpackById(Long epackId) {
        Optional<EpackEntity> epackEntity = epackRepository.findById(epackId);
        if (epackEntity.isPresent()) {
            return epackMapper.epackEntityToEpackDto(epackEntity.get());
        } else {
            log.error("Exception {} is thrown. Epack with ID " + epackId + " does not exist.", HttpStatus.CONFLICT);
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "Epack with ID " + epackId + " does not exist.");
        }
    }

    @Override
    public List<EpackDTO> findAll() {
        final List<EpackEntity> epacks = epackRepository.findAll(Sort.by("epackId"));
        return epacks.stream()
                .map(epackMapper::epackEntityToEpackDto)
                .collect(Collectors.toList());
    }
}
