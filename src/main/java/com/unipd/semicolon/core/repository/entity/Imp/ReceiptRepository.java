package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Receipt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
public class ReceiptRepository {
    private final Map<Integer, Receipt> receiptMap;

    public ReceiptRepository() {
        this.receiptMap = new HashMap<>();
    }

    /**
     * Adds a new receipt to the repository.
     *
     * @param receipt The receipt to add.
     * @throws RepositoryException If an error occurs while adding the receipt.
     */
    public void add(Receipt receipt) throws RepositoryException {
        if (receiptMap.containsKey(receipt.getId())) {
            throw new RepositoryException("Receipt with ID " + receipt.getId() + " already exists in the repository");
        }
        receiptMap.put(receipt.getId(), receipt);
    }

    /**
     * Retrieves a receipt from the repository.
     *
     * @param id The ID of the receipt to retrieve.
     * @return An Optional containing the receipt if it exists in the repository, or an empty Optional if it does not.
     */
    public Optional<Receipt> getById(int id) {
        return Optional.ofNullable(receiptMap.get(id));
    }

    /**
     * Updates an existing receipt in the repository.
     *
     * @param receipt The receipt to update.
     * @throws RepositoryException If the receipt does not exist in the repository.
     */
    public void update(Receipt receipt) throws RepositoryException {
        if (!receiptMap.containsKey(receipt.getId())) {
            throw new RepositoryException("Receipt with ID " + receipt.getId() + " does not exist in the repository");
        }
        receiptMap.put(receipt.getId(), receipt);
    }

    /**
     * Deletes a receipt from the repository.
     *
     * @param id The ID of the receipt to delete.
     * @throws RepositoryException If the receipt does not exist in the repository.
     */
    public void delete(int id) throws RepositoryException {
        if (!receiptMap.containsKey(id)) {
            throw new RepositoryException("Receipt with ID " + id + " does not exist in the repository");
        }
        receiptMap.remove(id);
    }

    /**
     * Retrieves all receipts from the repository.
     *
     * @return A list of all receipts in the repository.
     */
    public List<Receipt> getAll() {
        return List.copyOf(receiptMap.values());
    }
}
