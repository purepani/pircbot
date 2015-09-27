package com.company;
import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by panis on 9/27/15.
 */

/*
The actual bot
 */
public class SatwikpBot extends PircBot{


    ArrayList<Tuple<String, String>> ops;




    public SatwikpBot(){
        this.ops = new ArrayList<>();
        this.setName("satwikpbot");
        this.onServerPing("tmi.twitch.tv");
    }



    public void onMessage(String channel, String sender,
                          String login, String hostname, String message) {

        boolean op = isOp(channel, sender);

        if (message.equalsIgnoreCase("time") && op) {
            String time = new Date().toString();
            sendMessage(channel,".me " + sender + " : The time is now " + time);
        }

    }

    protected void onConnect(){
        this.sendRawLine("CAP REQ :twitch.tv/commands");
        this.sendRawLine("CAP REQ :twitch.tv/membership");
    }

    protected void onJoin(String channel, String sender, String login, String hostname){

        if(sender.equalsIgnoreCase(this.getName())) {
            sendMessage(channel, "I HAVE JOINED!!!");
        }
    }

    private boolean isOp(String channel, String user){

        User [] users = getUsers(channel);
        for(User us : users){
           String name = us.getNick();


            Tuple mods = new Tuple<>(channel, user);

            System.out.println(mods.showA() + " " + mods.showB());
            System.out.println(this.ops.indexOf(mods));

            if(name.equalsIgnoreCase(user) && this.ops.contains(mods)){
                return true;

            }

        }
        return false;
    }

    protected void onUserMode(String targetnick, String sourcenick, String sourcelogin, String sourcehostname, String mode){


            String channel = mode.substring(mode.indexOf("#"));
            channel = channel.substring(0, channel.indexOf(" "));

            if (mode.contains("+o")) {
                String realUser = mode.substring(mode.indexOf("+o") + 3);

                Tuple mods = new Tuple<>(channel, realUser);

               this.ops.add(mods);

                System.out.println(mods.showA() + " " + mods.showB());
                System.out.println(this.ops.indexOf(mods));


            } else if (mode.contains("-o")) {
                String realUser = mode.substring(mode.indexOf("-o") + 3);
                this.ops.remove(new Tuple<>(channel, realUser));
            }

    }

}
