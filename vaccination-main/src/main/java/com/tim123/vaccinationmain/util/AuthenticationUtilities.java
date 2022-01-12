package com.tim123.vaccinationmain.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AuthenticationUtilities {

    private static final String connectionUri = "xmldb:exist://%1$s:%2$s/exist/xmlrpc";

    static public class ConnectionProperties {

        public String host;
        public int port = -1;
        public String user;
        public String password;
        public String driver;
        public String uri;

        public ConnectionProperties(Properties props) {
            super();

            user = props.getProperty("conn.user").trim();
            password = props.getProperty("conn.password").trim();

            host = props.getProperty("conn.host").trim();
            port = Integer.parseInt(props.getProperty("conn.port"));

            uri = String.format(connectionUri, host, port);

            driver = props.getProperty("conn.driver").trim();
        }
    }

    public static ConnectionProperties loadProperties() throws IOException {
        String propsName = "exist.properties";

        InputStream propsStream = openStream(propsName);
        if (propsStream == null)
            throw new IOException("Could not read properties " + propsName);

        Properties props = new Properties();
        props.load(propsStream);

        return new ConnectionProperties(props);
    }

    public static InputStream openStream(String fileName) throws IOException {
        return AuthenticationUtilities.class.getClassLoader().getResourceAsStream(fileName);
    }
}
