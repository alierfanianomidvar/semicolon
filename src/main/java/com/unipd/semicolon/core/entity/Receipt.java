package com.unipd.semicolon.core.entity;


import java.util.*;
public class Receipt {

        private int id;
        private List<DrugEntry> drugs;
        private byte[] image;
        private Date date;
        private PaymentMethod paymentMethod;

        public Receipt(int id, List<DrugEntry> drugs, byte[] image, Date date, PaymentMethod paymentMethod) {
            this.id = id;
            this.drugs = drugs;
            this.image = image;
            this.date = date;
            this.paymentMethod = paymentMethod;
        }

        // getters and setters for all attributes

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<DrugEntry> getDrugs() {
            return drugs;
        }

        public void setDrugs(List<DrugEntry> drugs) {
            this.drugs = drugs;
        }

        public byte[] getImage() {
            return image;
        }

        public void setImage(byte[] image) {
            this.image = image;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public PaymentMethod getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        // method to calculate the total cost of the drugs on the receipt
        public double getTotalCost() {
            double total = 0;
            for (DrugEntry entry : drugs) {
                total += entry.getAmount() * entry.getPrice();
            }
            return total;
        }

        // inner class to represent an entry for a drug on the receipt
        public static class DrugEntry {
            private int drugId;
            private int amount;
            private double price;

            public DrugEntry(int drugId, int amount, double price) {
                this.drugId = drugId;
                this.amount = amount;
                this.price = price;
            }

            // getters and setters for all attributes

            public int getDrugId() {
                return drugId;
            }

            public void setDrugId(int drugId) {
                this.drugId = drugId;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }
    }

    // enum to represent the payment methods available
    enum PaymentMethod {
        CASH,
        CREDIT_CARD,
        DEBIT_CARD,
        PAYPAL
    }

    // method to create a new receipt
    public static Receipt create(int id, List<DrugEntry> drugs, byte[] image, Date date, PaymentMethod paymentMethod) {
        return new Receipt(id, drugs, image, date, paymentMethod);
    }

    // method to get the history of all receipts
    public static List<Receipt> history(List<Receipt> receipts) {
        return receipts;
    }

    // method to get the prescription for a specific drug on the receipt
    public int getPrescription(int drugId) {
        // logic to get the prescription for a specific drug
        return 0;
    }

    // method to edit the date (deadline) for the receipt
    public void editDate(Date newDate) {
        this.date = newDate;
    }

    // method to approve the receipt
    public void approve() {
        // logic to approve the receipt
    }
}
