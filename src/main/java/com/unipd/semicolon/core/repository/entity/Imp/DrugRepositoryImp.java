package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.repository.entity.DrugRepository;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DrugRepositoryImp extends CustomRepository implements DrugRepository {
    
    @Override
    public Drug Save(Drug drug){
        return save(Drug.class,drug);
    }
    
    @Override
    public List<Drug> getAll(){
        return ListQueryWrapper(entityManager.createQuery(
                "SELECT g FROM Drug g ORDER BY g.id desc ",Drug.class
        ));
    }

    private List<Drug> ListQueryWrapper(TypedQuery<Drug> query) {
    }
}
