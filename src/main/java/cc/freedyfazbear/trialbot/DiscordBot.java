package cc.freedyfazbear.trialbot;

import cc.freedyfazbear.trialbot.handler.CommandHandler;
import cc.freedyfazbear.trialbot.handler.MemberJoinHandler;
import cc.freedyfazbear.trialbot.handler.MessageHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public enum DiscordBot
{
    INSTANCE;
    public JDA api;

    public void init() {
        JDABuilder builder = JDABuilder.createDefault("token", GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS));

        builder.addEventListeners(new MessageHandler(), new CommandHandler(), new MemberJoinHandler());

        try {
            api = builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DiscordBot.INSTANCE.init();
    }

    public JDA getApi() {
        return api;
    }
}
