package com.drogbalog.server.global.util;

import com.drogbalog.server.global.code.OrderByType;
import org.springframework.data.domain.Sort;

public final class SortsUtil {

    private static Sort sortByIdDesc() {
        return Sort.by(OrderByType.ID.getDescription()).descending();
    }

}
