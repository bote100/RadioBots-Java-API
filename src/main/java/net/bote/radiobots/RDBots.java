package net.bote.radiobots;

import net.bote.radiobots.bots.enums.MusicBotLocation;
import net.bote.radiobots.bots.enums.RadioBotType;
import net.bote.radiobots.bots.objects.MusicBot;
import net.bote.radiobots.login.RadioBotsLoginSession;
import net.bote.radiobots.request.RBRequest;
import net.bote.radiobots.util.MapBuilder;
import net.bote.radiobots.util.MapPair;
import net.bote.radiobots.util.RBAPIAuth;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Elias Arndt | bote100
 * Created on 17.07.2019
 */

public class RDBots {

    private static HashMap<Integer, MusicBot> musicBotCache = new HashMap<>();

    public static MusicBot getMusicBot(int uuid, RBAPIAuth auth, RadioBotsLoginSession session) {
        if(musicBotCache.containsKey(uuid)) return musicBotCache.get(uuid);
        MusicBot musicBot = new MusicBot(uuid, auth, session);
        musicBotCache.put(uuid, musicBot);
        return musicBot;
    }

    public static MusicBot createMusicBot(RBAPIAuth auth, String nickname, String host, String serverpassword, MusicBotLocation location, RadioBotsLoginSession session) throws IOException {
        JSONObject callback = RBRequest.request(RadioBotType.TEAMSPEAK, "order", MapBuilder.buildStringMap(
                new MapPair("nick", nickname),
                new MapPair("ip", host),
                new MapPair("pw", serverpassword),
                new MapPair("location", location.toString().toUpperCase()),
                new MapPair("userid", String.valueOf(session.getUserid()))
                ), auth
        );

        if(!callback.getBoolean("success")) throw new RuntimeException(callback.getString("data"));

        return getMusicBot(callback.getInt("id"), auth, session);
    }

}
