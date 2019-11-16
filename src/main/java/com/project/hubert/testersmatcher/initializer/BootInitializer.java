package com.project.hubert.testersmatcher.initializer;

import com.project.hubert.testersmatcher.initializer.csv.CsvColumnsReader;
import com.project.hubert.testersmatcher.initializer.jpa.DbInitializer;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Log4j2
@Component
public class BootInitializer implements CommandLineRunner {
    private static final String PATH_PRX = "static";

    private CsvColumnsReader columnsReader;
    private DbInitializer dbInitializer;

    public BootInitializer(CsvColumnsReader columnsReader, DbInitializer dbInitializer) {
        this.columnsReader = columnsReader;
        this.dbInitializer = dbInitializer;
    }

    @Override
    public void run(String... args) {
        List<Map<String, String>> parsedTesters = columnsReader.readCsv(PATH_PRX + "/testers.csv");
        List<Map<String, String>> parsedDevices = columnsReader.readCsv(PATH_PRX + "/devices.csv");
        List<Map<String, String>> parsedTesterDevices = columnsReader.readCsv(PATH_PRX + "/tester_device.csv");
        List<Map<String, String>> parsedBugs = columnsReader.readCsv(PATH_PRX + "/bugs.csv");
        List<Map<String, String>> countries = prepareCountriesAndModifyOnTesters(parsedTesters);

        dbInitializer.initializeCountries(countries);
        dbInitializer.initializeTesters(parsedTesters);
        dbInitializer.initializeDevices(parsedDevices);
        dbInitializer.initializeTestersDevices(parsedTesterDevices);
        dbInitializer.initializeBugs(parsedBugs);

    }

    private List<Map<String, String>> prepareCountriesAndModifyOnTesters(List<Map<String, String>> parsedTesters) {
        AtomicLong nextId = new AtomicLong(1L);
        Map<String, Long> countriesWithIds = new HashMap<>();
        List<Map<String, String>> result = new ArrayList<>();
        String countryColumn = "country";
        parsedTesters.forEach(tester -> {
            String testerCountry = tester.get(countryColumn);
            if (StringUtils.isNotBlank(testerCountry)) {
                boolean countryAlreadyPresent = countriesWithIds.containsKey(testerCountry);
                Long id = countryAlreadyPresent ? countriesWithIds.get(testerCountry) : nextId.getAndIncrement();
                String country = tester.get(countryColumn);
                if (!countryAlreadyPresent) {
                    countriesWithIds.put(country, id);

                    Map<String, String> mappedCountry = new HashMap<>();
                    mappedCountry.put("id", String.valueOf(id));
                    mappedCountry.put("name", testerCountry);
                    result.add(mappedCountry);
                }
                tester.put(countryColumn, String.valueOf(id));  //replace the tester country with entity id
            }
        });
        return result;
    }
}
