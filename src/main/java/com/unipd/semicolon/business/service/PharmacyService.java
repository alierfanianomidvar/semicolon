package com.unipd.semicolon.business.service;
import com.unipd.semicolon.business.exception.CustomException;
import com.unipd.semicolon.core.domain.PharmacyResponse;
import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.Storage;
import com.unipd.semicolon.core.entity.TimeTable;
import com.unipd.semicolon.core.entity.User;

import java.util.List;

public interface PharmacyService {

    Pharmacy save(
            String name,
            String address,
            String tell_number,
            List<TimeTable> time_table,
            byte[] logo,
            List<Storage> storage,
            List<User> staff
    ) throws CustomException;
    Boolean edit(
            Long id,
            String name,
            String address,
            String tell_number,
            List<TimeTable> time_table,
            byte[] logo,
            List<Storage> storage,
            List<User> staff

    );

    Pharmacy get(Long id);

    //Add staff
    Boolean addStaff(List<User> user_list, Long id);
    //Delete staff
    Boolean deleteStaff(List<User> user_list);
    Boolean delete(Long id);
    List<Pharmacy> getAll();


    //Not sure if the list type should be user or userResponse
}