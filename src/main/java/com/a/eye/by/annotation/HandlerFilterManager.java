package com.a.eye.by.annotation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandlerFilterManager {

    private Map<FilterType, List<IHandlerFilter>> filtersMap = new HashMap<FilterType, List<IHandlerFilter>>();

    private Map<String, IHandlerFilter> filterNamesMap = new HashMap<String, IHandlerFilter>();

    public List<IHandlerFilter> getFilters(FilterType type) {
        return filtersMap.get(type);
    }

    public HandlerFilterManager() {
        for (FilterType type : FilterType.values()) {
            filtersMap.put(type, new ArrayList<IHandlerFilter>());
        }
    }

    public void doFilter() {
        for (IHandlerFilter filter : getFilters(FilterType.START)) {
            boolean isSuccess = filter.doFilter();
            if (!isSuccess) {
                break;
            }
        }
        for (IHandlerFilter filter : getFilters(FilterType.END)) {
            boolean isSuccess = filter.doFilter();
            if (!isSuccess) {
                break;
            }
        }
    }

    public void addFilter(IHandlerFilter filter) {
        FilterDesc filterDesc = filter.getClass().getAnnotation(FilterDesc.class);
        List<IHandlerFilter> filterList = this.getFilters(filterDesc.type());
        filterList.add(filter);
        filterNamesMap.put(filterDesc.name(), filter);
        Collections.sort(filterList, new Comparator<IHandlerFilter>() {
            public int compare(IHandlerFilter o1, IHandlerFilter o2) {
                FilterDesc f1 = o1.getClass().getAnnotation(FilterDesc.class);
                FilterDesc f2 = o2.getClass().getAnnotation(FilterDesc.class);
                int order1 = f1.order();
                int order2 = f2.order();
                if (order1 > order2) {
                    return 1;
                } else if (order1 < order2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }
}
