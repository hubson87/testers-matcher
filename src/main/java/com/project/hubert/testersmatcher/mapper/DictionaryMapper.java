package com.project.hubert.testersmatcher.mapper;

import com.project.hubert.testersmatcher.domain.model.DictionaryItem;
import com.project.hubert.testersmatcher.domain.model.entity.Country;
import com.project.hubert.testersmatcher.domain.model.entity.Device;

public class DictionaryMapper {
    public static DictionaryItem fromCountry(Country country) {
        if (country == null) {
            return null;
        }
        return DictionaryItem.builder().code(country.getCountryCode()).id(country.getId()).build();
    }

    public static DictionaryItem fromDevice(Device device) {
        if (device == null) {
            return null;
        }
        return DictionaryItem.builder().code(device.getDescription()).id(device.getDeviceId()).build();
    }
}
