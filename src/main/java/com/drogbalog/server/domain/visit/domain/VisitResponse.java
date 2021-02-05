package com.drogbalog.server.domain.visit.domain;

import lombok.Data;

@Data
public class VisitResponse {
    private long today;
    private long allDay;

    public void reset() {
        this.today = 0;
        this.allDay = 0;
    }
}
