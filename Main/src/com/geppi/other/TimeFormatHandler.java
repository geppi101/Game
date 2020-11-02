package com.geppi.other;

public class TimeFormatHandler {

    public String formatTime(long millis) {
        String format = "";

        long seconds = millis/1000;
        long minutes = seconds/60;
        long hours = minutes/60;
        long days = hours/24;

        long months = days/30;
        long years = months/12;

        seconds-=minutes*60;
        minutes-=hours*60;
        hours-=days*24;
        days-=months*30;
        months-=years*12;

        if(years>0) format += "Year" + (years > 1 ? "s" : "") + ": " + years;

        if(months>0) {
            if(!format.isEmpty()) format += " ";
            format+= "Month" + (months > 1 ? "s" : "") + ": " + months;
        }
        if(days>0) {
            if(!format.isEmpty()) format += " ";
            format+= "Day" + (days > 1 ? "s" : "") + ": " + days;
        }
        if(hours>0) {
            if(!format.isEmpty()) format += " ";
            format+= "Hour" + (hours > 1 ? "s" : "") + ": " + hours;
        }
        if(minutes>0) {
            if(!format.isEmpty()) format += " ";
            format+= "Minute" + (minutes > 1 ? "s" : "") + ": " + minutes;
        }
        if(seconds>0) {
            if(!format.isEmpty()) format += " ";
            format+= "Second" + (seconds > 1 ? "s" : "") + ": " + seconds;
        }
        return format;
    }

}
