package com.tim123.vaccinationmain.repository;

import com.tim123.vaccinationmain.util.AuthenticationUtilities;
import com.tim123.vaccinationmain.util.MarshallUnmarshallFactory;
import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringWriter;

@Component
public class RepositoryUtil {

    public String convertToXmlString(Object object, Class<?> objectClass) throws JAXBException {
        var marshaller = MarshallUnmarshallFactory.getMarshaller(objectClass);
        var stringWriter = new StringWriter();
        marshaller.marshal(object, stringWriter);
        return stringWriter.toString();
    }

    public void save(String collectionId, String documentId, String xmlData) throws Exception {
        AuthenticationUtilities.ConnectionProperties props = AuthenticationUtilities.loadProperties();

        // initialize database driver
        Class<?> cl = Class.forName(props.driver);

        // encapsulation of the database driver functionality
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        // entry point for the API which enables you to get the Collection reference
        DatabaseManager.registerDatabase(database);
        // a collection of Resources stored within an XML database
        Collection col = null;
        XMLResource res = null;

        try {
            col = getOrCreateCollection(collectionId, props);
            res = (XMLResource) col.createResource( documentId + ".xml", XMLResource.RESOURCE_TYPE);
            res.setContent(xmlData);
            col.storeResource(res);
        } finally {
            if (res != null) {
                try {
                    ((EXistResource) res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }

    private Collection getOrCreateCollection(String collectionUri,
                                             AuthenticationUtilities.ConnectionProperties props) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0, props);
    }

    private Collection getOrCreateCollection(String collectionUri,
                                             int pathSegmentOffset,
                                             AuthenticationUtilities.ConnectionProperties props) throws XMLDBException {
        Collection col = DatabaseManager
                .getCollection(props.uri + collectionUri, props.user, props.password);

        // create the collection if it does not exist
        if (col == null) {

            if (collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }

            String[] pathSegments = collectionUri.split("/");

            if (pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for (int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/").append(pathSegments[i]);
                }

                Collection startCol = DatabaseManager
                        .getCollection(props.uri + path, props.user, props.password);

                if (startCol == null) {
                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager
                            .getCollection(props.uri + parentPath, props.user, props.password);

                    CollectionManagementService mgt = (CollectionManagementService) parentCol
                            .getService("CollectionManagementService", "1.0");

                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);

                    col.close();
                    parentCol.close();
                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset, props);
        } else {
            return col;
        }
    }

    public String findByDocumentId(String collection, String documentId) throws IOException, ClassNotFoundException, XMLDBException, IllegalAccessException, InstantiationException {
        AuthenticationUtilities.ConnectionProperties props = AuthenticationUtilities.loadProperties();
        Collection col = null;

        Class<?> cl = Class.forName(props.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        try {
            col = DatabaseManager.getCollection(props.uri + "/" + collection);

            if (col == null) {
                return null;
            }
            var res = (XMLResource)col.getResource(documentId + ".xml");
            if (res != null) {
                return (String) res.getContent();
            }
        } catch(Exception e) {
          return "";
        } finally {
            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
        return "";
    }
}
