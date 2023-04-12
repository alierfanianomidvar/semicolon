package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Material;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface MaterialRepository {
    Material save(
            Material material
    );

    List<Material> getAll();

    Material findMaterialById(Long id);
}
