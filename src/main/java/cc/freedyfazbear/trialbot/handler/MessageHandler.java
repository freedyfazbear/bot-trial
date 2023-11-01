package cc.freedyfazbear.trialbot.handler;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class MessageHandler extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Member member = event.getMember();
        Message message = event.getMessage();

        if (message.getContentDisplay().equals("!ping")) message.reply("Pong!").queue();

        if (message.getContentDisplay().startsWith("!say")) message.reply(message.getContentDisplay().replace("!say", "")).queue();

        if (message.getContentDisplay().equals("*commands")) {
            event.getGuild().updateCommands().addCommands(Commands.slash("ban", "Ban mentioned person")
                    .addOption(OptionType.MENTIONABLE, "member", "Select what person", true),
                    Commands.slash("kick", "Kick mentioned person")
                            .addOption(OptionType.MENTIONABLE, "member", "Select what person", true),
                    Commands.slash("purge", "Purge specified number of messages").addOption(OptionType.INTEGER, "amount", "Amount of messages")).queue();
        }
    }
}
