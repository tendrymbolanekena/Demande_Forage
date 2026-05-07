package com.example.forage.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.forage.entity.Region;
import com.example.forage.repository.RegionRepository;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Region getRegionById(Long id) {
        return regionRepository.findById(id).orElse(null);
    }

    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }

    public void deleteRegion(Long id) {
        regionRepository.deleteById(id);
    }
}
