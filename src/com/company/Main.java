package com.company;
import org.jibble.pircbot.*;

public class Main {

    public static void main(String[] args) throws Exception{
        String pass = "oauth:1zx9d1or2zof5ay2os3zkpvinw5g1f";
        String channel = "#satwikp";
        SatwikpBot bot = new SatwikpBot();


        bot.setVerbose(true);
        bot.connect("irc.twitch.tv", 6667, pass);
        bot.joinChannel("#satwikp");


    }
}
