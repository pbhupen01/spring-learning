package practice.spring.springmvc.services;

import org.springframework.stereotype.Service;
import practice.spring.springmvc.model.UnitOfMeasure;
import practice.spring.springmvc.repositories.UnitOfMeasureRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository)
    {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Set<UnitOfMeasure> listAllUoms() {
        Set<UnitOfMeasure> unitOfMeasures = new HashSet();
        unitOfMeasureRepository.findAll().forEach(unitOfMeasures::add);
        return unitOfMeasures;
    }
}
