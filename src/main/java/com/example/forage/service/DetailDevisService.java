package com.example.forage.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.forage.repository.DetailDevisRepository;

@Service
@Transactional
public class DetailDevisService {

    @Autowired
    private DetailDevisRepository detailDevisRepository;

    public DetailDevis saveDetailDevis(DetailDevis detailDevis) {
        return detailDevisRepository.save(detailDevis);
    }

    public DetailDevis getAllDetailDevisByIdDevis(Long id) {
        return detailDevisRepository.findAllByIdDevis(id);
    }

    public void saveAllDetailDevis(List<DetailDevis> detailDevisList) {
        detailDevisRepository.saveAll(detailDevisList);
    }


}