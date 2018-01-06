package com.tpg.ocs.persistence.entities;

import com.tpg.ocs.util.DateGeneration;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.sql.Date;

import static java.util.Calendar.APRIL;
import static java.util.Calendar.OCTOBER;
import static org.assertj.core.api.Assertions.assertThat;

public class LocalDateAttributeConverterTest implements DateGeneration {

    private LocalDate localDate;

    private LocalDateAttributeConverter converter;

    @Before
    public void setUp() {
        localDate = generateDate(23, 10, 1985);

        converter = new LocalDateAttributeConverter();
    }

    @Test
    public void convertToDatabaseColumn() {

        Date actual = converter.convertToDatabaseColumn(localDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(actual);

        CalendarAssertion.assertThat(calendar).hasDate(23).hasMonth(OCTOBER).hasYear(1985);
    }

    @Test
    public void convertToEntityAttribute() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, APRIL, 17);
        Date date = Date.valueOf(localDate);

        LocalDate actual = converter.convertToEntityAttribute(date);

        assertThat(actual).isEqualTo(localDate);
    }
}
