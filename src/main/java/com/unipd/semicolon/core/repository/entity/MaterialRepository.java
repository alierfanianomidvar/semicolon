package com.unipd.semicolon.core.repository.entity;

import com.unipd.semicolon.core.entity.Material;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MaterialRepository {
    Material save(Material material);

    public List<Material> getAll();

    public Material findById(Long id);
}
