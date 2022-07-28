package com.systemdesign.urlshortener.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Slf4j
public class SimpleEncoder {
    private String BASE_STRING;
    private Integer ENCODING_LENGTH;
    private HashMap<Character, Integer> characterIntegerHashMap;

    public SimpleEncoder(@Value("${base.string}")String BASE_STRING, @Value("${encoding.length}")Integer ENCODING_LENGTH) {
        this.BASE_STRING = BASE_STRING;
        this.ENCODING_LENGTH = ENCODING_LENGTH;
        this.characterIntegerHashMap = new HashMap<>();
        for(int i = 0;i<BASE_STRING.length(); i++) {
            characterIntegerHashMap.put(BASE_STRING.charAt(i), i);
        }
    }


    public String encodeIntegerWithWithPadding(Long inputValue) {
        log.info("Value to encode is {}", inputValue);
        String result = encodeInteger(inputValue);
        result = addPadding(result);
        return result;
    }

    public String encodeInteger(Long inputValue) {
        Integer baseLength = BASE_STRING.length();
        log.info("Base length is {}", baseLength);
        StringBuilder result = new StringBuilder();
        while (inputValue > 0) {
            long remainder = inputValue % baseLength;
            result.append(BASE_STRING.charAt((int) remainder));
            inputValue = inputValue/baseLength;
        }
        log.info("Encoded value is {}", result.reverse()); // A12
        return result.toString();
    }

    public String addPadding(String inputValue) {
        // Exception thrown
        int paddingLen = ENCODING_LENGTH - inputValue.length();
        StringBuilder paddedString = new StringBuilder();

        log.info("Padding length is {}, {}", paddingLen, inputValue);
        for(int i=0;i<paddingLen;i++) {
            paddedString.append(BASE_STRING.charAt(0));
        }
        paddedString.append(inputValue);
        log.info("Padded encoded string is {}", paddedString);
        return paddedString.toString();
    }

    public Long getDecodedIndex(String shortURL) {
        Long index = 0l;
        for (int i = 0 ; i<shortURL.length(); i++) {
            int currVal = characterIntegerHashMap.get(shortURL.charAt(i));
//            log.info("Char {}, value {}",shortURL.charAt(i), characterIntegerHashMap.get(shortURL.charAt(i)));
            index = index*(BASE_STRING.length()) + currVal;
        }
        log.info("Decoded index is {}", index);
        return index;
    }
}
