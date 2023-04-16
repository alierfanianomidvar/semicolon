package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.Storage;
import com.unipd.semicolon.core.repository.entity.StorageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class StorageRepositoryImp extends CustomRepository implements StorageRepository {

    @Transactional
    @Override
    public Storage save(Storage storage) {

        return save(Storage.class, storage);
    }

    @Override
    public boolean delete(Storage storage) {

        delete(Storage.class, storage);
        return true;
    }

    @Override
    public Storage findStorageById(Long id) {

        return findById(Storage.class, id);
    }

    @Override
    public List<Storage> getAll() {
        return listQueryWrapper(entityManager.createQuery(
                "select g from Storage g order by g.id desc ",
                Storage.class));
    }
}
