package ru.job4j.storesql;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Сlass SAXPars.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 20.04.2019
 */
public class SAXPars extends DefaultHandler {
    private int sum;

    /**
     * Returns the value of the operation(sum).
     *
     * @return sum type int
     */
    public int getSum() {
        return sum;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("entry".equals(qName)) {
            this.sum += Integer.parseInt(attributes.getValue(0));
        }
    }
}
