package ru.job4j.storesql;

import org.junit.Test;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreSQLTest {
    @Test
    public void whenAddFiveElementsInDBThenResultTen() throws Exception {
        StoreSQL storeSQL = new StoreSQL(new Config());
        StoreXML storeXML = new StoreXML(new File("src/main/resources/source.xml"));
        long start = System.currentTimeMillis();
        storeSQL.start(5);
        List<Entry> entries = storeSQL.load();
        storeXML.save(entries);
        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(
                new File("src/main/resources/source.xml"),
                new File("src/main/resources/dest.xml"),
                new File("src/main/resources/scheme.xml"));
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SAXPars saxp = new SAXPars();
        parser.parse(
                new File("src/main/resources/dest.xml"),
                saxp
        );
        assertThat(saxp.getSum(), is(10));
        System.out.println(System.currentTimeMillis() - start);
    }
}