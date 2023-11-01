package cc.freedyfazbear.trialbot.handler;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class CommandHandler extends ListenerAdapter
{
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        if (member == null) return;

        switch (event.getName()) {
            case "ban" -> {
                Member target = event.getOption("member").getAsMember();
                if (target == null) {
                    event.reply("Specified member does not exist").queue();
                    return;
                }

                if (!member.hasPermission(Permission.BAN_MEMBERS)) {
                    event.reply("You can't ban this person").queue();
                    return;
                }

                event.reply(target.getAsMention() + " has been banned").queue();

                target.ban(7, TimeUnit.DAYS).queue();
            }

            case "kick" -> {
                Member target = event.getOption("member").getAsMember();
                if (target == null) {
                    event.reply("Specified member does not exist").queue();
                    return;
                }

                if (!member.hasPermission(Permission.KICK_MEMBERS)) {
                    event.reply("You can't kick this person").queue();
                    return;
                }

                event.reply(target.getAsMention() + " has been kicked").queue();

                target.kick().queue();
            }

            case "purge" -> {
                int amount = event.getOption("amount").getAsInt();

                if (!member.hasPermission(Permission.MESSAGE_MANAGE)) {
                    event.reply("You can't purge messages").queue();
                    return;
                }

                if (amount > 100) {
                    event.reply("The amount cant be bigger than 100").queue();
                    return;
                }

                event.getChannel().getHistory().retrievePast(amount).queue(messages -> {
                    event.getChannel().purgeMessages(messages);
                    event.reply("Purged: **" + messages.size() + "** messages!").queue();
                });
            }
        }
    }
}
