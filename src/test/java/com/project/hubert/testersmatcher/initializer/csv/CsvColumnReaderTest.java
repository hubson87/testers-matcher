package com.project.hubert.testersmatcher.initializer.csv;

import com.project.hubert.testersmatcher.initializer.csv.impl.CsvColumnsReaderImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CsvColumnsReaderImpl.class)
public class CsvColumnReaderTest {

    @Resource
    CsvColumnsReader csvColumnsReader;

    @Test
    public void shouldMapEmptyFile() {
        assertThat(csvColumnsReader.readCsv("/notexisting")).isEmpty();
    }

    @Test
    public void shouldMapExampleFile() {
        //when
        List<Map<String, String>> res = csvColumnsReader.readCsv("example.csv");

        assertThat(res).isNotNull();
        assertThat(res.size()).isEqualTo(3);
        assertThat(res.get(0).get("name")).isEqualTo("n1");
        assertThat(res.get(0).get("value")).isEqualTo("v1");
        assertThat(res.get(1).get("name")).isEqualTo("n2");
        assertThat(res.get(1).get("value")).isNull();
        assertThat(res.get(2).get("name")).isEqualTo(null);
        assertThat(res.get(2).get("value")).isEqualTo("v3");
    }
}
