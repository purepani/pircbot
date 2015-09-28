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
        ops = new ArrayList<>();

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

    protected void onUserMode(String targetnick, String sourcenick, String sourcelogin, String sourcehostname, String mode){


        String channel = mode.substring(mode.indexOf("#"));
        channel = channel.substring(0, channel.indexOf(" "));

        if (mode.contains("+o")) {
            String realUser = mode.substring(mode.indexOf("+o") + 3);

            Tuple mods = new Tuple<>(channel, realUser);

            ops.add(mods);
            System.out.println(mods.showA() + " " + mods.showB());
            System.out.println(ops.indexOf(mods));


        } else if(mode.contains("-o")){
            String realUser = mode.substring(mode.indexOf("+o") + 3);

            Tuple mods = new Tuple(channel, realUser);

            ops.add(mods);

        }

    }

    private boolean isOp(String channel, String user){

        User[] users = getUsers(channel);

        Tuple mods = new Tuple<>(channel, user);

        System.out.println(mods.showA() + " " + mods.showB());
        System.out.println(ops.indexOf(mods));

        for(User us : users){
           String name = us.getNick();

            if(name.equalsIgnoreCase(user) && ops.contains(mods)){
                return true;

            }

        }
        return false;
    }

    public String ops(){
        return (ops.get(0).showA() + " " + ops.get(0).showB());
    }



}
