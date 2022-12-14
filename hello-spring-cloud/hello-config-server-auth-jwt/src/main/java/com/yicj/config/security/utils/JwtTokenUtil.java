package com.yicj.config.security.utils;

import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;

@Component
public class JwtTokenUtil implements Serializable {

    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final String CLAIM_KEY_AUDIENCE = "audience" ;

    private static final String CLAIM_KEY_CREATED = "created" ;

    private static final String AUDIENCE_UNKNOWN = "unknown" ;

    private static final String AUDIENCE_WEB = "web" ;

    private Key secret = MacProvider.generateKey() ;



}
