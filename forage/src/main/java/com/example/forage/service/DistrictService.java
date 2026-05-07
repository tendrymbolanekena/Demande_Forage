package com.example.forage.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.forage.entity.District;
import com.example.forage.repository.DistrictRepository;

@Service
@Transactional
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    public District getDistrictById(Long id) {
        return districtRepository.findById(id).orElse(null);
    }

    public List<District> getDistrictsByRegion(Long idRegion) {
        return districtRepository.findByRegionIdRegion(idRegion);
    }

    public District saveDistrict(District district) {
        return districtRepository.save(district);
    }

    public void deleteDistrict(Long id) {
        districtRepository.deleteById(id);
    }
}
