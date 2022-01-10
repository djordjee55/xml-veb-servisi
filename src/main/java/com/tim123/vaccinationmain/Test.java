package com.tim123.vaccinationmain;

import com.tim123.vaccinationmain.model.interesovanje.Interesovanje;
import com.tim123.vaccinationmain.service.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@AllArgsConstructor
public class Test implements CommandLineRunner {

    private final InteresovanjeService interesovanjeService;
    private final IzvestajService izvestajService;
    private final PotvrdaService potvrdaService;
    private final SaglasnostService saglasnostService;
    private final SertifikatService sertifikatService;
    private final ZahtevService zahtevService;

    private static final String path = "src/main/resources/static/xml";

    @Override
    public void run(String... args) throws Exception {
        writeFilesTest();
        readFilesTest();
        System.out.println("####################################################################");
        test();
    }

    private String readFile(String path)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    private void writeFilesTest() throws Exception{
        System.out.println("Writing interesovanje instance to database...");
        String content = readFile(path+"/interesovanje.xml");
        interesovanjeService.save("1", content);
        System.out.println("Interesovanje instance successfully added.");
        System.out.println("------------------------------------------");

        System.out.println("Writing izvestaj instance to database...");
        content = readFile(path+"/izvestaj.xml");
        izvestajService.save("1", content);
        System.out.println("Izvestaj instance successfully added.");
        System.out.println("------------------------------------------");

        System.out.println("Writing potvrda instance to database...");
        content = readFile(path+"/potvrda.xml");
        potvrdaService.save("1", content);
        System.out.println("Potvrda instance successfully added.");
        System.out.println("------------------------------------------");

        System.out.println("Writing saglasnost instance to database...");
        content = readFile(path+"/saglasnost.xml");
        saglasnostService.save("1", content);
        System.out.println("Saglasnost instance successfully added.");
        System.out.println("------------------------------------------");

        System.out.println("Writing sertifikat instance to database...");
        content = readFile(path+"/sertifikat.xml");
        sertifikatService.save("1", content);
        System.out.println("Sertifikat instance successfully added.");
        System.out.println("------------------------------------------");

        System.out.println("Writing zahtav instance to database...");
        content = readFile(path+"/zahtev.xml");
        zahtevService.save("1", content);
        System.out.println("zahtev instance successfully added.");
        System.out.println("------------------------------------------");
    }

    private void readFilesTest() throws Exception{
        XMLResource resource = interesovanjeService.findByDocumentId("1.xml");
        if(resource != null)
            System.out.println(resource.getContent());

        System.out.println("--------------------------------------");

        resource = izvestajService.findByDocumentId("1.xml");
        if(resource != null)
            System.out.println(resource.getContent());

        System.out.println("--------------------------------------");

        resource = potvrdaService.findByDocumentId("1.xml");
        if(resource != null)
            System.out.println(resource.getContent());

        System.out.println("--------------------------------------");

        resource = saglasnostService.findByDocumentId("1.xml");
        if(resource != null)
            System.out.println(resource.getContent());

        System.out.println("--------------------------------------");

        resource = sertifikatService.findByDocumentId("1.xml");
        if(resource != null)
            System.out.println(resource.getContent());

        System.out.println("--------------------------------------");

        resource = zahtevService.findByDocumentId("1.xml");
        if(resource != null)
            System.out.println(resource.getContent());
    }


    void test() throws Exception {
        Interesovanje i = interesovanjeService.unmarshall("1.xml");
        if(i!=null)
            System.out.println(i.getZeljenaOpstinaVakcinacije());

        System.out.println(interesovanjeService.marshall(i));


    }

}
