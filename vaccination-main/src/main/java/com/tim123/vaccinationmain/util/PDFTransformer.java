package com.tim123.vaccinationmain.util;

import com.tim123.vaccinationmain.model.potvrda.Potvrda;
import com.tim123.vaccinationmain.model.sertifikat.Sertifikat;
import net.sf.saxon.TransformerFactoryImpl;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Component
public class PDFTransformer {

    public static final String FOX_XCONF = "classpath:static/fop.xconf";
    public static final String PDF_FILE = "classpath:gen/fo/out.pdf";
    public static String XSL_FILE;
    private final FopFactory fopFactory;
    private final TransformerFactory transformerFactory;

    public PDFTransformer() throws SAXException, IOException {
        fopFactory = FopFactory.newInstance(ResourceUtils.getFile(FOX_XCONF));
        transformerFactory = new TransformerFactoryImpl();
    }

    private void setXSLFile(Class<?> classOfDocument) {
        if (classOfDocument.equals(Sertifikat.class))
            XSL_FILE = "classpath:static/xsl-fo/sertifikat-fo.xsl";
        else if (classOfDocument.equals(Potvrda.class))
            XSL_FILE = "classpath:static/xsl-fo/potvrda-fo.xsl";

    }

    public ByteArrayInputStream generatePDF(String documentXml, Class<?> classOfDocument) {
        try {
            setXSLFile(classOfDocument);

            File xslFile = ResourceUtils.getFile(XSL_FILE);
            StreamSource transformSource = new StreamSource(xslFile);
            StreamSource source = new StreamSource(new StringReader(documentXml));
            FOUserAgent userAgent = fopFactory.newFOUserAgent();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();

            Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);
            Result res = new SAXResult(fop.getDefaultHandler());
            xslFoTransformer.transform(source, res);
            return new ByteArrayInputStream(outStream.toByteArray());
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

}
