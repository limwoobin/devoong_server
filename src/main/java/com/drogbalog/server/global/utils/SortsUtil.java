package com.drogbalog.server.global.utils;

import com.drogbalog.server.global.code.OrderByType;
import org.springframework.data.domain.Sort;

public final class SortsUtil {

    private static Sort sortByIdAsc() {
        return Sort.by(OrderByType.ID.getDescription()).ascending();
    }

    private static Sort sortByIdDesc() {
        return Sort.by(OrderByType.ID.getDescription()).descending();
    }

    private static Sort sortByCreatedDateAsc() {
        return Sort.by(OrderByType.CREATED_DATE.getDescription()).ascending();
    }

    private static Sort sortByCreatedDateDesc() {
        return Sort.by(OrderByType.CREATED_DATE.getDescription()).descending();
    }

    private static Sort sortByModifiedDateAsc() {
        return Sort.by(OrderByType.MODIFIED_DATE.getDescription()).ascending();
    }

    private static Sort sortByModifiedDateDesc() {
        return Sort.by(OrderByType.MODIFIED_DATE.getDescription()).descending();
    }
}
