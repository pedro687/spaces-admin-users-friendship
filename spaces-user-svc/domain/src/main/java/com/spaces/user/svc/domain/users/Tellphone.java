package com.spaces.user.svc.domain.users;

import com.spaces.user.svc.domain.ValueObjects;

public class Tellphone extends ValueObjects {
    private String number;
    private String countryCode;
    private String areaCode;

    private Tellphone(String number, String countryCode, String areaCode) {
        this.number = number;
        this.countryCode = countryCode;
        this.areaCode = areaCode;
    }

    public static Tellphone with(String number, String countryCode, String areaCode) {
        return new Tellphone(number, countryCode, areaCode);
    }
}
