package com.systemdesign.urlshortener.service;

import com.systemdesign.urlshortener.ExceptionHandler.SomeThingWentWrongException;
import com.systemdesign.urlshortener.ExceptionHandler.URLNotFoundException;
import com.systemdesign.urlshortener.constants.AppConstants;
import com.systemdesign.urlshortener.entity.UrlMapping;
import com.systemdesign.urlshortener.repository.UrlMappingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

@Service
@Slf4j
public class URLShortenerServiceImpl implements URLShortenerService {

    @Value("${url.expiration.days}")
    private Integer expirationDays;

    private UrlMappingRepository urlMappingRepository;
    private SimpleEncoder simpleEncoder;

    public URLShortenerServiceImpl(@Autowired SimpleEncoder simpleEncoder, @Autowired UrlMappingRepository urlMappingRepository) {
        this.simpleEncoder = simpleEncoder;
        this.urlMappingRepository = urlMappingRepository;
    }


    public String getBigURL(String shortURL) throws NullPointerException {

        Long index = simpleEncoder.getDecodedIndex(shortURL);
        Optional<UrlMapping> result = urlMappingRepository.findById(index);

        if(!result.isPresent()) {
            throw new URLNotFoundException(AppConstants.NOT_FOUND);
        }
        UrlMapping urlMapping = result.get();
        log.info("UrlMapping to return is {}", urlMapping);
        return urlMapping.getBigUrl();
    }

    @Override
    public String addBigURL(String bigURL) {

        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate expirationDate = LocalDate.now().plusDays(expirationDays);

            UrlMapping urlMapping = urlMappingRepository.save(new UrlMapping(bigURL, currentDate, expirationDate));

            log.info("UrlMapping saved is {}", urlMapping);
            String encodedString = simpleEncoder.encodeIntegerWithWithPadding(urlMapping.getId());

            return encodedString;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new SomeThingWentWrongException(AppConstants.WENT_WRONG);
        }
    }
}
