package com.example.demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneNumberInfo {
    // Make sure that the field names exactly match the JSON keys from the API response.
    private boolean valid;
    private String number;
    private String local_format;
    private String international_format;
    private String country_prefix;
    private String country_code;
    private String country_name;
    private String carrier;
    private String line_type;
}