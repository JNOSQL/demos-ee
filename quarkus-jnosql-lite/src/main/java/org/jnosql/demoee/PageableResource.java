package org.jnosql.demoee;

import jakarta.data.repository.Direction;
import jakarta.data.repository.Pageable;
import jakarta.data.repository.Sort;

import java.util.Arrays;
import java.util.Objects;

public interface PageableResource {


    default Pageable createPageable(String orderBy, int page, int pageSize) {
        return Pageable.ofSize(pageSize).page(page)
                .sortBy(Arrays.stream(orderBy.split(","))
                        .map(this::convertToSort)
                        .filter(Objects::nonNull)
                        .toList());
    }


    default Sort convertToSort(String sort) {
        String[] sortParam = sort.split(":");
        if (sortParam.length == 1 && !sortParam[0].isBlank()) {
            return Sort.ascIgnoreCase(sortParam[0]);
        }
        try {
            if (sortParam[0].isBlank()) {
                return null;
            }
            Direction direction = Direction.valueOf(sortParam[1].toUpperCase());
            return Sort.of(sortParam[0], direction, true);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

}
