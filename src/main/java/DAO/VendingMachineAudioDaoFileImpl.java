package DAO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;


public class VendingMachineAudioDaoFileImpl implements VendingMachineAudioDao {

    public static final String AUDIT_FILE = "audit.txt"; // text file to where all the audits go

    @Override
    public void writeAuditEntry(String audit) throws VendingMachinePersistenceException { // function to write to file
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not persist audit information.", e);
        }
        LocalDateTime timestamp = LocalDateTime.now(); // gets the date/time at the current point
        out.println(timestamp + " : " + audit);
        out.flush();
        out.close();
    }
}




