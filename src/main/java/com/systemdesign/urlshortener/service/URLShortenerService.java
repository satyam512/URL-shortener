package com.systemdesign.urlshortener.service;

public interface URLShortenerService {

    /**
     * Method to get original Big URL from short URL if it exists
     * @param shortURL
     * @return
     */
    String getBigURL(String shortURL);

    /**
     * Method to add a Big URL and generate a short URL for the same
     * @param bigURL
     * @return
     */
    String addBigURL(String bigURL);
}
