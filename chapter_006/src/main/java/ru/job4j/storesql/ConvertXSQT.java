package ru.job4j.storesql;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Сlass ConvertXSQT.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 20.04.2019
 */
public class ConvertXSQT {

    /**
     * Transformation the source file to dest with the scheme.
     *
     * @param source type File.
     * @param dest   type File.
     * @param scheme type File.
     * @throws TransformerException If an unrecoverable error occurs
     *                              during the course of the transformation.
     */
    public void convert(File source, File dest, File scheme) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(scheme));
        transformer.transform(new StreamSource(source), new StreamResult(dest));
    }
}
