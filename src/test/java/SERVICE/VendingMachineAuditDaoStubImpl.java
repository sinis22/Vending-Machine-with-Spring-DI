package SERVICE;


import DAO.VendingMachineAudioDao;

public class VendingMachineAuditDaoStubImpl implements VendingMachineAudioDao {
    @Override
    public void writeAuditEntry(String entry) {
        // testing. does nothing
    }
}
