package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.repository.entity.DrugRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DrugRepositoryImp extends CustomRepository implements DrugRepository {
    @Transactional
    @Override
    public Drug save(Drug drug) {
        return save(Drug.class,drug);
    }

    @Override
    public List<Drug> getAll(){
        return listQueryWrapper(entityManager.createQuery(
                "SELECT g FROM Drug g ORDER BY g.id desc ",Drug.class
        ));
    }

    public Drug findDrugById(Long id) {
        return null;
    }
}
