package com.tim123.vaccinationportal.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchUtil {

    private static final String DOCUMENTS_DOMAIN = "http://www.xws.org";

    public String parseSearchResult(List<String> documents, String documentName, String searchedString) {

        StringBuilder returnValue = new StringBuilder();
        returnValue.append(String.format("<%s>", documentName));

        documents.stream().filter(document -> document.toLowerCase().contains(searchedString.toLowerCase()) || searchedString.equals("")).forEach(document -> {

            String documentId = extractDocumentIdFromAbout(document, documentName);

            returnValue.append("<DocumentId>").append(documentId).append("</DocumentId>");
        });

        returnValue.append(String.format("</%s>", documentName));

        return returnValue.toString();
    }

    private String extractDocumentIdFromAbout(String document, String documentName) {

        String about = String.format("about=\"%s/%s#", DOCUMENTS_DOMAIN, documentName);

        return document.split(about)[1].split("\"")[0];
    }
}
