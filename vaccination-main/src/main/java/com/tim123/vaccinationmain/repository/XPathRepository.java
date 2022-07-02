package com.tim123.vaccinationmain.repository;

import com.tim123.vaccinationmain.util.AuthenticationUtilities;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

@Component
public class XPathRepository {

    public ResourceSet executeXPath(String collectionId, String expression, String targetNamespace) throws Exception {
        ResourceSet result;

        AuthenticationUtilities.ConnectionProperties props = AuthenticationUtilities.loadProperties();

        Class<?> cl = Class.forName(props.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;

        try {
            col = DatabaseManager.getCollection(props.uri + collectionId);

            if (col == null) {
                return null;
            }
            XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "2.0");
            xpathService.setProperty("indent", "yes");

            xpathService.setNamespace("", targetNamespace);
            result = xpathService.query(expression);
        } finally {

            // don't forget to cleanup
            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
        return result;
    }
}
