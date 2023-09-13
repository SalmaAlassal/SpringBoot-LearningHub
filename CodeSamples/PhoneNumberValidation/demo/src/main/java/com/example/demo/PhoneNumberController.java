package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class PhoneNumberController {
    // The @Value annotation is used to inject values from the application properties file.
    @Value("${numverify.api.key}")
    private String numVerifyApiKey;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/validate")
    public String validatePhoneNumber(String phoneNumber, String countryCode, Model model) {
        String apiUrl = "http://apilayer.net/api/validate?access_key=" + numVerifyApiKey
                + "&number=" + phoneNumber
                + "&country_code=" + countryCode
                + "&format=1";

        // The RestTemplate class is used to make HTTP requests.
        RestTemplate restTemplate = new RestTemplate();
        // The getForObject method makes a GET request to the specified URL and returns the response as an object.
        // It takes two arguments: the URL and the class of the object that the response should be converted to.
        // The class must have fields that match the JSON keys from the API response.
        PhoneNumberInfo phoneNumberInfo = restTemplate.getForObject(apiUrl, PhoneNumberInfo.class);

        model.addAttribute("phoneNumberInfo", phoneNumberInfo);
        return "result";
    }
}