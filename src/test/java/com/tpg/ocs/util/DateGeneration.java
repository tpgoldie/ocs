package com.tpg.ocs.util;

import java.time.LocalDate;

public interface DateGeneration {

    default LocalDate generateDate(int date, int month, int year) {

        return LocalDate.of(year, month, date);
    }
}
