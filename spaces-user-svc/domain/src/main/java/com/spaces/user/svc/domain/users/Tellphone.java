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

    public static Tellphone fromString(String numberTellphone) {
        String countryCode = numberTellphone.substring(0, 2);
        String areaCode = numberTellphone.substring(2, 4);
        String number = numberTellphone.substring(4);

        return new Tellphone(number, countryCode, areaCode);
    }

    @Override
    public String toString() {
        return countryCode +
                areaCode +
                number;
    }
}
