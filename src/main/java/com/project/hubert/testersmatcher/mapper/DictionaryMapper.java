package com.project.hubert.testersmatcher.mapper;

import com.project.hubert.testersmatcher.domain.model.DictionaryItem;
import com.project.hubert.testersmatcher.domain.model.entity.Country;
import com.project.hubert.testersmatcher.domain.model.entity.Device;

public class DictionaryMapper {
    public static DictionaryItem fromCountry(Country country) {
        if (country == null) {
            return null;
        }
        return DictionaryItem.builder().code(country.getCountryCode()).name(country.getCountryCode()).build();
    }

    public static DictionaryItem fromDevice(Device device) {
        if (device == null) {
            return null;
        }
        return DictionaryItem.builder().code(device.getName()).name(device.getName()).build();
    }
}
