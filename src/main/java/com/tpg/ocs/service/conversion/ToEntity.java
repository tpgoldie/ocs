package com.tpg.ocs.service.conversion;

import org.springframework.core.convert.converter.Converter;

public interface ToEntity<T, U> extends Converter<T, U> {
}
