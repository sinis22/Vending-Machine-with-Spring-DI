package DAO;

public interface VendingMachineAudioDao {
    void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
