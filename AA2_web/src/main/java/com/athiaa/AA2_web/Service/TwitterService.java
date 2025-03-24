//package com.athiaa.AA2_web.Service;
//
//import org.springframework.stereotype.Service;
//import twitter4j.Twitter;
//import twitter4j.TwitterException;
//import twitter4j.Status;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class TwitterService {
//
//    private final Twitter twitter;
//
//    public TwitterService(Twitter twitter) {
//        this.twitter = twitter;
//    }
//
//    public List<String> getTweets(String username) throws TwitterException {
//        return twitter.getUserTimeline(username).stream()
//                .map(Status::getText)
//                .collect(Collectors.toList());
//    }
//}



package com.athiaa.AA2_web.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class TwitterService {

    @Value("${twitter.api.bearer-token}")
    private String bearerToken;

    private final WebClient webClient;

    // Constructor-based injection of WebClient
    public TwitterService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.twitter.com/2").build();
    }

    // Fetch tweets by username
    public String fetchTweets(String username) {
        try {
            // Use WebClient to fetch tweets from Twitter API V2
            return webClient.get()
                    .uri("/users/by/username/{username}/tweets", username)
                    .header("Authorization", "Bearer " + bearerToken)
                    .retrieve()
                    .bodyToMono(String.class) // Convert response body to String
                    .block(); // Block to wait for the result (for simplicity in this example)
        } catch (WebClientResponseException e) {
            return "Error fetching tweets: " + e.getResponseBodyAsString();
        }
    }

    // Post a tweet
    public String postTweet(String tweetContent) {
        try {
            // Use WebClient to post a tweet
            return webClient.post()
                    .uri("/tweets")
                    .header("Authorization", "Bearer " + bearerToken)
                    .bodyValue("{\"status\": \"" + tweetContent + "\"}") // JSON body with tweet content
                    .retrieve()
                    .bodyToMono(String.class) // Convert response body to String
                    .block(); // Block to wait for the result
        } catch (WebClientResponseException e) {
            System.out.println("Error posting tweet: " + e.getResponseBodyAsString());
            return "Error posting tweet: " + e.getResponseBodyAsString();
        }
    }
}
