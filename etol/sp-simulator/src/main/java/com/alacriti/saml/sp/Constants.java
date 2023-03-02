package com.alacriti.saml.sp;

public class Constants {
    
    public static final String CookieName = "SP-COOKIE";
    public static final int CookieExpirySeconds = 5 * 60;
    public static final String SAMLAssertionUrlInSP = "http://google.com";
    public static final String SAMLRequestUrlInIdP = "http://localhost:8081/idp/samlRequest?userId=abc";
}
