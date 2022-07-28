package com.systemdesign.urlshortener.ExceptionHandler;

//@ResponseStatus(HttpStatus.NOT_FOUND)   // this is actually a controller as it's returning a response, so if we include it in out centralized ExceptionResponseHandler thing , do we require this annotation  check, Answer No, we don't need it any more
public class URLNotFoundException extends RuntimeException{
    public URLNotFoundException(String message) {
        super(message);
    }
}
