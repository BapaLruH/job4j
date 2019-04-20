package ru.job4j.storesql;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * Сlass StoreXML.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 20.04.2019
 */
public class StoreXML {
    private final File target;

    public StoreXML(File target) {
        this.target = target;
    }


    public void save(List<Entry> list) throws Exception {
        JAXBContext context = JAXBContext.newInstance(StoreSQL.Entries.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(new StoreSQL.Entries(list), target);
    }
}
