package com.main.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class NumberClassificationService {

    private final ObjectMapper objectMapper;


    public NumberClassificationService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<String> classifyNumbers(String number) {
        if (!number.matches("-?\\d+")) {
            return buildErrorResponse("alphabet");
        }

        int num = Integer.parseInt(number);
        return ResponseEntity.ok().header("Content-Type", "application/json").body(handleValidNumber(num));
    }

    private ResponseEntity<String> buildErrorResponse(String number) {
        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("number", number);
        responseMap.put("error", true);
        String response = convertToJson(responseMap);
        log.info("The number provided is invalid:: {}", response);
        return ResponseEntity.badRequest().header("Content-Type", "application/json").body(response);
    }

    private String handleValidNumber(int num) {
        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("number", num);
        responseMap.put("is_prime", isPrime(num));
        responseMap.put("is_perfect", isPerfect(num));
        responseMap.put("properties", getProperties(num));
        responseMap.put("digit_sum", getDigitSum(num));
        responseMap.put("fun_fact", getFunFact(num));

        String response = convertToJson(responseMap);
        log.info("The number properties are:: {}", response);
        return response;
    }

    private int getDigitSum(int num) {
        return String.valueOf(num).chars().map(Character::getNumericValue).sum();
    }

    private String[] getProperties(int num) {
        List<String> properties = new ArrayList<>();
        if (isArmstrong(num)) properties.add("armstrong");
        properties.add(num % 2 == 0 ? "even" : "odd");
        return properties.toArray(new String[0]);
    }

    private boolean isArmstrong(int num) {
        int originalNum = num, sum = 0, digits = String.valueOf(num).length();
        while (originalNum > 0) {
            int digit = originalNum % 10;
            sum += Math.pow(digit, digits);
            originalNum /= 10;
        }
        return sum == num;
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private boolean isPerfect(int num) {
        int sum = 1;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i != num / i) sum += num / i;
            }
        }
        return sum == num && num != 1;
    }

    private String getFunFact(int num) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://numbersapi.com/" + num + "/math";
        return restTemplate.getForObject(apiUrl, String.class);
    }

    private String convertToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error converting object to JSON", e);
            return "{}";
        }
    }
}
