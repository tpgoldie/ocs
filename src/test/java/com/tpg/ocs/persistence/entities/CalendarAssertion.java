package com.tpg.ocs.persistence.entities;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.util.Calendar;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class CalendarAssertion extends AbstractAssert<CalendarAssertion, Calendar> {

    public static CalendarAssertion assertThat(Calendar calendar) {
        return new CalendarAssertion(calendar);
    }

    public CalendarAssertion(Calendar calendar) {
        super(calendar, CalendarAssertion.class);
    }

    public CalendarAssertion hasDate(int value) {

        Assertions.assertThat(actual.get(DATE)).isEqualTo(value);

        return this;
    }

    public CalendarAssertion hasMonth(int value) {

        Assertions.assertThat(actual.get(MONTH)).isEqualTo(value);

        return this;
    }

    public CalendarAssertion hasYear(int value) {

        Assertions.assertThat(actual.get(YEAR)).isEqualTo(value);

        return this;
    }
}
