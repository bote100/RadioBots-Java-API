package net.bote.radiobots.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bote.radiobots.bots.RadioBot;

/**
 * @author Elias Arndt | bote100
 * Created on 17.07.2019
 */

@AllArgsConstructor
@Getter
public class RadioBotStream {

    private String url;
    private String position;
    private String lenght;
    private boolean paused;
    private boolean playing;
    private RadioBot bot;

    public void resume() {
        bot.seek(this.position);
        bot.play(bot.getAuth(), this.url);
    }

    public void pause() {
        this.bot.pause();
    }

}
