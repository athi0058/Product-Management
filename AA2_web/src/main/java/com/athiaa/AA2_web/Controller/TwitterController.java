//package com.athiaa.AA2_web.Controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import twitter4j.Status;
//import twitter4j.Twitter;
//import twitter4j.TwitterException;
//import twitter4j.User;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/twitter")
//@CrossOrigin
//public class TwitterController {
//
//    @Autowired
//    private Twitter twitter;
//
//    // Fetch latest tweet by username
//    @GetMapping("/fetch/{username}")
//    public String fetchTweet(@PathVariable String username) {
//        try {
//            User user = twitter.showUser(username);
//            if (user.getStatus() != null) {
//                return "Latest tweet by @" + username + ": " + user.getStatus().getText();
//            } else {
//                return "@" + username + " has not tweeted yet.";
//            }
//        } catch (TwitterException e) {
//            return "Error fetching tweet: " + e.getMessage();
//        }
//    }
//
//    // Post a tweet
//    @PostMapping("/post")
//    public String postTweet(@RequestBody Map<String, String> payload) {
//        String tweetText = payload.get("tweetContent");
//        System.out.println(tweetText);
//        try {
//            Status status = twitter.updateStatus(tweetText);
//            System.out.println("posted successfully...");
//            return "Successfully posted tweet: " + status.getText();
//        } catch (TwitterException e) {
//            System.out.println("error...posting");
//            return "Error posting tweet: " + e.getMessage();
//        }
//    }
//
//}
/*====================================================================*/
package com.athiaa.AA2_web.Controller;

import com.athiaa.AA2_web.Service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/twitter")
@CrossOrigin
public class TwitterController {

    private final TwitterService twitterService;

    @Autowired
    public TwitterController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    // Fetch tweets for a username
    @GetMapping("/fetch/{username}")
    public String fetchTweet(@PathVariable String username) {
        return twitterService.fetchTweets(username);
    }

    // Post a tweet
    @PostMapping("/post")
    public String postTweet(@RequestBody Map<String, String> payload) {
        System.out.println(payload.get("tweetContent"));
        return twitterService.postTweet(payload.get("tweetContent"));
    }
}
