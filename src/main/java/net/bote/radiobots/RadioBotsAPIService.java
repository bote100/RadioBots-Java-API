package net.bote.radiobots;

import net.bote.radiobots.bots.objects.MusicBot;
import net.bote.radiobots.login.RBLoginCallBack;
import net.bote.radiobots.login.RadioBotLogin;
import net.bote.radiobots.login.RadioBotsLoginSession;
import net.bote.radiobots.util.RBAPIAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Elias Arndt | bote100
 * Created on 17.07.2019
 */

/**
 * This is only a class to test all functions of this api!
 */
public class RadioBotsAPIService {

    private static RBAPIAuth rbapiAuth;
    private static RadioBotsLoginSession loginSession;

    public static void main(String[] args) throws IOException, InterruptedException {
        rbapiAuth = new RBAPIAuth("bote100", VarNotPushed.TOKEN);

        loginSession = new RadioBotLogin(VarNotPushed.EMAIL, VarNotPushed.PASSWORD, rbapiAuth).login(new RBLoginCallBack() {

            @Override
            public void onFailure(String data) {
                System.out.println(data);
                System.exit(0);
            }

            @Override
            public void onSuccess(RadioBotsLoginSession login) {
                System.out.println("Gained access to account (" + login.getUserid() + ") => " + login.getRadioBotLogin().getEmail());
            }

        });

        MusicBot bot = RDBots.getMusicBot(258, rbapiAuth, loginSession);
        bot.start();
        bot.changeName("RENAME NR 1");
        bot.play(rbapiAuth, bot.getRadioBotStream().getUrl());
        System.out.println(bot.getName() + " - " + bot.getServerName() + " Vol: " + bot.getVolume());
        System.out.println(bot.getSettings().getCreation());
        Thread.sleep(5000);
        bot.pause();
        Thread.sleep(4000);
        bot.play(rbapiAuth, bot.getRadioBotStream().getUrl());
        Thread.sleep(10000);
        bot.changeName("RENAMED BOT!");
        bot.play(rbapiAuth, "https://stream01.zoneradio.de/zoneradio_hq");
        Thread.sleep(20000);
        bot.changeServerAndConnect("ts.radiobots.eu");
        bot.setCommander();
        Thread.sleep(5000);
        Runtime.getRuntime().addShutdownHook(new Thread(bot::shutdown));
        listen();
    }

    public static RBAPIAuth getAuthentication() {
        return rbapiAuth;
    }

    private static void listen() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.length() == 0) return;
                if(line.equals("stop")) System.exit(0);
            }
        } catch (Exception ex) {}
    }

}