package yourestack.epack.business.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yourestack.epack.business.domain.AddressDTO;
import yourestack.epack.business.mappers.AddressMapStruct;
import yourestack.epack.business.model.AddressEntity;
import yourestack.epack.business.model.repos.AddressRepository;
import yourestack.epack.business.service.AddressService;
import yourestack.epack.util.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapStruct addressMapper;

    @Override
    public List<AddressDTO> findAll() {
        final List<AddressEntity> address = addressRepository.findAll(Sort.by("addressId"));
        return address.stream()
                .map(addressMapper::addressEntityToAddressDto)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO get(Long addressId) {
        return addressRepository.findById(addressId)
                .map(addressMapper::addressEntityToAddressDto)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public AddressDTO create(AddressDTO addressDTO) {

        AddressEntity addressEntity = addressRepository.save(addressMapper.addressDtoToAddressEntity(addressDTO));
        log.info("New address added: {}", addressEntity);

        return addressMapper.addressEntityToAddressDto(addressEntity);
    }

    @Override

    public AddressDTO update(Long addressId, AddressDTO addressDTO) {
        final AddressEntity addressEntity = addressRepository.findById(addressId)
                .orElseThrow(NotFoundException::new);
        addressMapper.updateEntity(addressDTO, addressEntity);
        addressRepository.save(addressEntity);
        return addressMapper.addressEntityToAddressDto(addressEntity);
    }

    @Override
    public void delete(Long addressId) {

        addressRepository.deleteById(addressId);

    }

}
