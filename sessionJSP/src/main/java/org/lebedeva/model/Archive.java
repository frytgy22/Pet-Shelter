package org.lebedeva.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Archive {
    private List<CdDisk> cdDiskList;
    private Integer lastId;
}
