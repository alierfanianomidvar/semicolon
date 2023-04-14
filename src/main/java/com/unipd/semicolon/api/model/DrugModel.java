package com.unipd.semicolon.api.model;

import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.entity.enums.Gender;

import java.time.LocalDate;

public class DrugModel {

        private Long id;
        private String name;
        private Supplier supplier;
        private LocalDate expirationDate;
        private byte[] image;
        private String shape;
        private Gender gender;
        private AgeGroup ageGroup;
        private boolean isSensitive;
        private boolean needPrescription;
        private String description;
        private int limitation;
        private float price;
        private Country countryOFProduction;

        public DrugModel(){

        }

        public DrugModel(
                Long id,
                String name,
                Supplier supplier,
                LocalDate expirationDate,
                byte[] image,
                String shape,
                Gender gender,
                AgeGroup ageGroup,
                boolean isSensitive,
                boolean needPrescription,
                String description,
                int limitation,
                float price,
                Country countryOFProduction) {

                this.id = id;
                this.name = name;
                this.supplier = supplier;
                this.expirationDate = expirationDate;
                this.image = image;
                this.shape = shape;
                this.gender = gender;
                this.ageGroup = ageGroup;
                this.isSensitive = isSensitive;
                this.needPrescription = needPrescription;
                this.description = description;
                this.limitation = limitation;
                this.price = price;
                this.countryOFProduction = countryOFProduction;
        }

        public Long getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Supplier getSupplier() {
                return supplier;
        }

        public void setSupplier(Supplier supplier) {
                this.supplier = supplier;
        }

        public LocalDate getExpirationDate() {
                return expirationDate;
        }

        public void setExpirationDate(LocalDate expirationDate) {
                this.expirationDate = expirationDate;
        }

        public byte[] getImage() {
                return image;
        }

        public void setImage(byte[] image) {
                this.image = image;
        }

        public String getShape() {
                return shape;
        }

        public void setShape(String shape) {
                this.shape = shape;
        }

        public Gender getGender() {
                return gender;
        }

        public void setGender(Gender gender) {
                this.gender = gender;
        }

        public AgeGroup getAgeGroup() {
                return ageGroup;
        }

        public void setAgeGroup(AgeGroup ageGroup) {
                this.ageGroup = ageGroup;
        }

        public boolean isSensitive() {
                return isSensitive;
        }

        public void setSensitive(boolean sensitive) {
                isSensitive = sensitive;
        }

        public boolean isNeedPrescription() {
                return needPrescription;
        }

        public void setNeedPrescription(boolean needPrescription) {
                this.needPrescription = needPrescription;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public int getLimitation() {
                return limitation;
        }

        public void setLimitation(int limitation) {
                this.limitation = limitation;
        }

        public float getPrice() {
                return price;
        }

        public void setPrice(float price) {
                this.price = price;
        }

        public Country getCountryOFProduction() {
                return countryOFProduction;
        }

        public void setCountryOFProduction(Country countryOFProduction) {
                this.countryOFProduction = countryOFProduction;
        }

}
