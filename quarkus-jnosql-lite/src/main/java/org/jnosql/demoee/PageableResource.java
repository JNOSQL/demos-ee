package org.jnosql.demoee;


import jakarta.data.Direction;
import jakarta.data.page.PageRequest;
import jakarta.data.Sort;


public interface PageableResource {


    default PageRequest createPageable(String orderBy, int page, int pageSize) {
        return PageRequest.ofPage(page).size(pageSize);
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
