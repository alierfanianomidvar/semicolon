package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.repository.entity.MaterialRepository;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@Repository
public class MaterialRepositoryImp extends CustomRepository implements MaterialRepository {

    @Transactional
    @Override
    public Material save(Material material) {
        return save(Material.class,material);
    }

    @Override
    public List<Material> getAll() {
        return listQueryWrapper(entityManager.createQuery(
                "SELECT g FROM Material g ORDER BY g.id desc ",
        Material.class));
    }


    @Override
    public Material findMaterialById(Long id) {
        return findById(Material.class,id);
    }
}
