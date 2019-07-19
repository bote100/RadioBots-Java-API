# RadioBots Java API

RadioBots one of the popular free teamspeak music bots hoster. With this API you have access to **create and manage own 
music bots** and using other functions of the RadioBots-REST-API (as example a login checker) **with using java**.

### Goals
    * Simple usement of the Rest-API as a very important design goal.
    * Easy code structure
    * Coverage application and future usement
    
### Download

Maven:
```xml
        <repository>
            <id>mvn-repo</id>
            <url>https://rawgit.com/bote100/RadioBots-Java-API/master</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
        
        <dependency>
            <groupId>net.bote</groupId>
            <artifactId>radiobotsapi</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

Or [download RadioBots Java API jar file](https://workupload.com/file/nssDpG96) from workuplaod.com.

### Documentation

Setup RBAuth and login to your RadioBots account:

````java
import net.bote.radiobots.login.RBLoginCallBack;
import net.bote.radiobots.login.RadioBotLogin;
import net.bote.radiobots.login.RadioBotsLoginSession;
import net.bote.radiobots.util.RBAPIAuth;

public class LoginToAccount {

    private static RBAPIAuth rbapiAuth;
    private static RadioBotsLoginSession loginSession;

    public static void main(String[] args) throws IOException {
        
        // Setup a API authentication (to check intern permissions)
        rbapiAuth = new RBAPIAuth("USERNAME", "API_TOKEN");
        
        // Login to RadioBots account and react to both actions.
        //
        // If the login is successful, the functions contains a session object. This class contains all values of
        // the login process.
        loginSession = new RadioBotLogin("PASSWORD", "EMAIL", rbapiAuth).login(new RBLoginCallBack() {

            @Override
            public void onFailure(String data) {
                System.exit(0);
            }

            @Override
            public void onSuccess(RadioBotsLoginSession login) {
                System.out.println("Got access to account (" + login.getUserid() + ") => " + login.getRadioBotLogin().getEmail());
            }

        });
    }

}
````

Create a music bot and pause the music after a specific interval:

````java
import net.bote.radiobots.bots.objects.MusicBot;
import net.bote.radiobots.login.RBLoginCallBack;
import net.bote.radiobots.login.RadioBotLogin;
import net.bote.radiobots.login.RadioBotsLoginSession;
import net.bote.radiobots.util.RBAPIAuth;

public class TestBotMethods {

    public static void main(String[] args) throws IOException, InterruptedException {
        
        // Get the music bot with UUID 258 (see UUID in your RadioBots Interface)
        // transmit session and api authentication parameters too.
        MusicBot bot = RDBots.getMusicBot(258, rbapiAuth, loginSession);
        
        // Wait 5 seconds
        Thread.sleep(5000);
        
        // Pause music
        bot.pause();
        
        // Wait again 5 seconds
        Thread.sleep(5000);
        
        // Resume the old music
        bot.play(rbapiAuth, bot.getRadioBotStream().getUrl());
    }
}
````

**And much more...!**

### License

The RadioBots Java API is licensed under the [MIT License](LICENSE).
