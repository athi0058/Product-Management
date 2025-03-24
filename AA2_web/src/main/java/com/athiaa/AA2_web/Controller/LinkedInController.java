package com.athiaa.AA2_web.Controller;

import com.athiaa.AA2_web.Model.LinkedInPostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/linkedin")
@CrossOrigin
public class LinkedInController {

    @PostMapping("/post")
    public ResponseEntity<?> postToLinkedIn(@RequestBody LinkedInPostRequest postRequest) {
        String accessToken = "AQXx-voeV7d2h0liEWjs59i1rFfjmp99tJ33MWll95vSxMRmEk9zLgSi38Zi-a8lr70L-rh0BtXBqt45XhJ9Gf8y5WA_eBi8I0zJYzoS_T_0i9IDQRD5Lq3sbZOf2o4cl4_qno0ePviYvg3f0nL4dwMPCEawnArsmcAaEx6yPT6xLG7iuvI5wsgCIPVHA5fik-UDZKXbf2jGLIhaClainCErUkj3vWeQ8vMMi5PaR5VB9BqM3qshuScWND9DSf8ugN9JNFISvvL-uSqjEGZ9y9qOY9bbB02UhDJ-dIdY3eYeSSSGr6EXYS1R-fPN9WShUCRIiMG2UrqXVRcqc_cL-u7B-zbqvA";
        String url = "https://api.linkedin.com/v2/ugcPosts";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("X-Restli-Protocol-Version", "2.0.0");
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Convert the request object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(postRequest);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error parsing request body: " + e.getMessage());
        }

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error posting to LinkedIn: " + e.getMessage());
        }
    }

}

