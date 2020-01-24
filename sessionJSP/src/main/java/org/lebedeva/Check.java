package org.lebedeva;

import org.lebedeva.model.Archive;
import org.lebedeva.model.CdDisk;

public interface Check {
    static boolean checkParameter(String data) {
        return data != null && !data.isEmpty();
    }

    static CdDisk getCdDisk(Archive archive, Integer id) {

        for (CdDisk cdDisk : archive.getCdDiskList()) {
            if (cdDisk.getId().compareTo(id) == 0) {
                return cdDisk;
            }
        }
        return null;
    }
}
