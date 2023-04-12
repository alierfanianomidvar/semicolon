package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.repository.entity.MaterialRepository;

import java.util.List;

public class MaterialRepositoryImp extends CustomRepository implements MaterialRepository {

    @Override
    public Material save(Material
    material) {
        return save(Material.class,material);
    }

    @Override
    public List<Material> getAll() {
        return listQueryWrapper(entityManager.createQuery(
                "SELECT g FROM Material g ORDER BY g.id desc ",
        Material.class));
    }
}
