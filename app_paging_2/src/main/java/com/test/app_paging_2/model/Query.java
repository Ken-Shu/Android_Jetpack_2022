package com.test.app_paging_2.model;

public class Query {
    private static String searchQueryStr;

    public static String getSearchQueryStr() {
        return searchQueryStr;
    }

    public static void setSearchQueryStr(String searchQueryStr) {
        Query.searchQueryStr = searchQueryStr;
    }
}
