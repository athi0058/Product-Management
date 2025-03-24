package com.athiaa.AA2_web.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class TwitterConfig {

    @Value("${twitter.api.key}")
    private String apiKey;

    @Value("${twitter.api.secret}")
    private String apiSecret;

    @Value("${twitter.access.token}")
    private String accessToken;

    @Value("${twitter.access.secret}")
    private String accessSecret;

    @Value("${twitter.bearer.key}")
    private String bearerKey;

    @Bean
    public Twitter twitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(apiKey)
                .setOAuthConsumerSecret(apiSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessSecret);

        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }
}
