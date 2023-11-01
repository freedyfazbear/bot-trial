package cc.freedyfazbear.trialbot.handler;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MemberJoinHandler extends ListenerAdapter
{
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        TextChannel welcomeChannel = event.getGuild().getTextChannelById("id");
        Member member = event.getMember();

        if (welcomeChannel == null) throw new RuntimeException("Welcome channel is null! Please create welcome channel and set id");

        welcomeChannel.sendMessage("Welcome " + member.getAsMention() + " to our server. You are the **" + event.getGuild().getMembers().size() + "** member").queue();
    }
}
