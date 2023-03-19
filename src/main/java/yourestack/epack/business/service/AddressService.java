package yourestack.epack.business.service;


import yourestack.epack.business.domain.AddressDTO;

import java.util.List;

public interface AddressService {

    List<AddressDTO> findAll();

    AddressDTO get(Long addressId);

    AddressDTO create(AddressDTO addressDTO);

    AddressDTO update(Long addressId, AddressDTO addressDTO);

    void delete(Long addressId);
}
