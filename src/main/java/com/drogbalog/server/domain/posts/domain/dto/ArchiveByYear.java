package com.drogbalog.server.domain.posts.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveByYear implements Comparable<ArchiveByYear> {
    private String createdYear;
    private List<Archive> archives;

    @Override
    public int compareTo(ArchiveByYear o) {
        return o.createdYear.compareTo(this.createdYear);
    }
}
