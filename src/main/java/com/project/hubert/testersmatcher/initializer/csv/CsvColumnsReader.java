package com.project.hubert.testersmatcher.initializer.csv;

import java.util.List;
import java.util.Map;

public interface CsvColumnsReader {
    List<Map<String, String>> readCsv(String filePath);
}
