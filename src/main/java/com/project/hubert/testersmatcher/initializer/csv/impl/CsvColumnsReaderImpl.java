package com.project.hubert.testersmatcher.initializer.csv.impl;

import com.project.hubert.testersmatcher.initializer.csv.CsvColumnsReader;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Log4j2
@Component
public class CsvColumnsReaderImpl implements CsvColumnsReader {
    private static final String SEPARATOR = ",";

    @Override
    public List<Map<String, String>> readCsv(String filePath) {
        List<Map<String, String>> result = new ArrayList<>();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (is == null) {
                log.error("Cannot find file: " + filePath);
                return result;
            }
            try (InputStreamReader isr = new InputStreamReader(is); BufferedReader reader = new BufferedReader(isr)) {
                String line;
                boolean headerLine = true;
                List<String> headerNames = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    Map<String, String> row = new HashMap<>();
                    if (StringUtils.isBlank(line)) {
                        continue;
                    }
                    String[] tokenized = line.split(SEPARATOR);
                    clearQuotes(tokenized);
                    if (headerLine) {
                        headerNames = Arrays.asList(tokenized);
                        headerLine = false;
                    } else {
                        for (int i = 0; i < headerNames.size(); i++) {
                            String value = tokenized.length > i ? tokenized[i] : null;
                            if (StringUtils.isBlank(value)) {
                                value = null;
                            }
                            row.put(headerNames.get(i), value);
                        }
                        result.add(row);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Cannot parse provided file: " + filePath + ". Reason: " + e.getMessage(), e);
        }
        return result;
    }

    private void clearQuotes(String[] elements) {
        for (int i = 0; i < elements.length; i++) {
            if (StringUtils.isNotBlank(elements[i])) {
                elements[i] = StringUtils.trim(elements[i]).replaceAll("^\"|\"$", "");
            }
        }
    }
}
