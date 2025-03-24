const express = require('express');
const axios = require('axios');
const dotenv = require('dotenv');
const passport = require('passport');
const TwitterStrategy = require('passport-twitter').Strategy;

dotenv.config();

const app = express();
const port = 3000;

// Set up Passport for Twitter OAuth
passport.use(new TwitterStrategy({
  consumerKey: process.env.TWITTER_API_KEY,
  consumerSecret: process.env.TWITTER_API_SECRET,
  callbackURL: "http://localhost:3000/auth/twitter/callback"
}, async (token, tokenSecret, profile, done) => {
  // Save user profile and tokens (should store these securely in a database)
  profile.token = token;
  profile.tokenSecret = tokenSecret;
  return done(null, profile);
}));

passport.serializeUser((user, done) => done(null, user));
passport.deserializeUser((user, done) => done(null, user));

app.use(passport.initialize());
app.use(passport.session());

// Routes
app.get('/auth/twitter', passport.authenticate('twitter'));

app.get('/auth/twitter/callback',
  passport.authenticate('twitter', { failureRedirect: '/' }),
  (req, res) => {
    res.redirect('/');
  });

// Endpoint to post a tweet
app.post('/postTweet', async (req, res) => {
  const { tweetContent } = req.body; // Get the tweet content

  // Make a POST request to Twitter's API to send the tweet
  const url = 'https://api.twitter.com/2/tweets';
  const data = {
    status: tweetContent
  };

  const headers = {
    Authorization: `Bearer ${req.user.token}`  // Authorization with OAuth Bearer Token
  };

  try {
    const response = await axios.post(url, data, { headers });
    res.send(`Successfully posted tweet: ${response.data}`);
  } catch (error) {
    res.status(500).send(`Error posting tweet: ${error.message}`);
  }
});

app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});
