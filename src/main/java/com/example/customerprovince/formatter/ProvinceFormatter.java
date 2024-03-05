package com.example.customerprovince.formatter;

import com.example.customerprovince.model.Province;
import com.example.customerprovince.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class ProvinceFormatter implements Formatter<Province> {
    private final IProvinceService iProvinceService;
    @Autowired
    public ProvinceFormatter(IProvinceService iProvinceService) {
        this.iProvinceService = iProvinceService;
    }
    @Override
    public Province parse(String text, Locale locale) throws ParseException {
        Optional<Province> provinceOptional = iProvinceService.findById(Long.valueOf(text));
        return provinceOptional.orElse(null);
    }

    @Override
    public String print(Province object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
