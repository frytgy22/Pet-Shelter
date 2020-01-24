package org.lebedeva.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CdDisk implements Comparable<CdDisk> {
    private Integer id;
    private String name;
    private String singer;

    @Override
    public int compareTo(CdDisk o) {
        return this.getId().compareTo(o.id);
    }
}
