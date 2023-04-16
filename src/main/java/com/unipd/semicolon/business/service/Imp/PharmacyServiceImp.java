package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.CustomException;
import com.unipd.semicolon.business.service.PharmacyService;
import com.unipd.semicolon.core.entity.*;
import com.unipd.semicolon.core.repository.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PharmacyServiceImp implements PharmacyService {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private TTableRepository tTableRepository;

//    @Autowired
//    private StorageRepository storageRepository;

    // super admin only
    @Override
    public Pharmacy save(String name,
                         String address,
                         String tell_number,
                         List<TimeTable> time_table,
                         byte[] logo_path,
                         List<Storage> storages,
                         List<User> staff
    ) throws CustomException {

        if (name == null) throw new CustomException("Name is not specified!");
        if (address == null) throw new CustomException("Address is not specified!");
        if (time_table == null) throw new CustomException("time_table can not be null! Pass an empty list instead");
        if (storages == null) throw new CustomException("storage can not be null! Pass an empty list instead");
        if (staff == null) throw new CustomException("staff can not be null! Pass an empty list instead");

        //pharmacyRepository.findPharmacyByIdExists(1L);

        if (pharmacyRepository.getPharmacyByName(name) == null) {
            Pharmacy pharmacy = new Pharmacy(name,
                    address,
                    tell_number,
                    logo_path);
            Pharmacy save = pharmacyRepository.save(pharmacy);

            if (!time_table.isEmpty()) {
                for (TimeTable t : time_table) {
                    if (t.getPharmacy() != null) {
                        //  drug exits !!!
                        TimeTable timeTable = new TimeTable(
                                t.getId(),
                                t.getFrom_hour(),
                                t.getTo_hour()
                        );

//                        timeTableRepository.save(timeTable);
                    }
                    // Check if storage item has a valid ID
                    //TODO: After merge uncomment and debug
//                Storage storageItem = storageRepository.findStorageByPharmacyId(s.getId());
//                if(storageItem == null) {
//                    // ID is invalid, create a new Storage item for this Pharmacy
//                    storageItem = new Storage(save.getId(), save, null, null, 0, 0);
//                    storageRepository.save(storageItem);
//                }

                }
            }



            if (!storages.isEmpty()) {
                for (Storage s : storages) {
                    if (s.getDrug() != null) {
                        //  drug exits !!!
                        Storage storage = new Storage(
                                save,
                                s.getDrug(),
                                null,
                                s.getAmount(),
                                s.getThreshold()
                        );

//                         storageRepository.save(storage);
                    } else if (s.getMaterial() != null) {
                        // material exits !!
                        Storage storage = new Storage(
                                save,
                                null,
                                s.getMaterial(),
                                s.getAmount(),
                                s.getThreshold()
                        );
//                        storageRepository.save(storage);
                    }
                    // Check if storage item has a valid ID
                    //TODO: After merge uncomment and debug
//                Storage storageItem = storageRepository.findStorageByPharmacyId(s.getId());
//                if(storageItem == null) {
//                    // ID is invalid, create a new Storage item for this Pharmacy
//                    storageItem = new Storage(save.getId(), save, null, null, 0, 0);
//                    storageRepository.save(storageItem);
//                }

                }
            }
            if (!staff.isEmpty()) {
                for (User user : staff) {
                    User userById = userRepository.findUserById(user.getId());
                    if(userById == null) throw new CustomException("The user with id " + user.getId() + "does not exist!");
                    userById.setPharmacy(save);
                    userRepository.save(userById);
                }
            }
            return save;
        }

        return null;
    }

    @Override
    public Boolean edit(Long id,
                        String name,
                        String address,
                        String tell_number,
                        List<TimeTable> time_table,
                        byte[] logo,
                        List<Storage> storage,
                        List<User> staff
    ) {
        Optional<Pharmacy> pharmacyOptional = pharmacyRepository.findById(id);
        if (pharmacyOptional.isPresent()) {
            Pharmacy pharmacy = pharmacyOptional.get();
            if (name != null) pharmacy.setName(name);
            if (address != null) pharmacy.setAddress(address);
            if (tell_number != null) {
                //Check if Phone number is valid
                String regex = "(\\+39|0039)?(3[0-9]{2})(\\s|-)?([0-9]{7})";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(tell_number);
                if (matcher.matches()) {
                    pharmacy.setTelephoneNumber(tell_number);
                } else {
                    throw new CustomException("Invalid phone number!");
                }
            }
            if (time_table != null) pharmacy.setTime_table(time_table);
            if (logo != null) pharmacy.setLogo(logo);
            if (staff != null) pharmacy.setStorage(storage);
            pharmacyRepository.save(pharmacy);

            return true;
        } else {
            return null;
        }
    }

    @Override
    public Pharmacy get(Long id) {
        Optional<Pharmacy> pharmacyOptional = pharmacyRepository.findById(id);
        return pharmacyOptional.orElse(null);
    }

    @Override
    public Boolean addStaff(List<User> staffList, Long pharmacyId) {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
                .orElseThrow(() -> new CustomException("Pharmacy with ID " + pharmacyId + " not found!"));

        for (User staffMember : staffList) {
            User existingStaffMember = userRepository.findUserById(staffMember.getId());
            if (existingStaffMember != null) {
                // Update existing user's pharmacy
                existingStaffMember.setPharmacy(pharmacy);
                userRepository.save(existingStaffMember);
            } else {
                User newStaffMember = new User(
                        staffMember.getName(),
                        staffMember.getLastName(),
                        staffMember.getGender(),
                        staffMember.getBirthDate(),
                        staffMember.getPhoneNumber(),
                        staffMember.getAddress(),
                        staffMember.getRole(),
                        staffMember.getEmail(),
                        staffMember.getAccountStatus(),
                        staffMember.getProfilePicture(),
                        pharmacy
                );
                userRepository.save(newStaffMember);
            }
        }

        return true;
    }

    @Override
    public Boolean deleteStaff(List<User> staffList) {
        for (User staffMember : staffList) {
            User existingStaffMember = userRepository.findUserById(staffMember.getId());
            if (existingStaffMember != null) {
//                User user = existingStaffMember;
                if (existingStaffMember.getPharmacy() != null && existingStaffMember.getPharmacy().getId().equals(staffMember.getPharmacy().getId())) {
                    existingStaffMember.setPharmacy(null);
                    userRepository.save(existingStaffMember);
                } else {
                    throw new CustomException("Pharmacy ID " + staffMember.getPharmacy().getId() + " does not match with the user's pharmacy ID!");
                }
            } else {
                throw new CustomException("Staff member with ID " + staffMember.getId() + " not found!");
            }
        }

        return true;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Pharmacy> pharmacyOptional = pharmacyRepository.findById(id);
        if (pharmacyOptional.isPresent()) {
            // Remove rows from other tables referencing this Pharmacy
            userRepository.deleteByPharmacyId(id);
            tTableRepository.deleteByPharmacyId(id);
//            storageRepository.deleteByPharmacyId(id);

            // Remove the Pharmacy object
            pharmacyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Pharmacy> getAll() {
        return pharmacyRepository.findAll();
    }

}