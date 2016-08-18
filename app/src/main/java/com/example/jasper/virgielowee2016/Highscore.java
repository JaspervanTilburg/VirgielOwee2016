package com.example.jasper.virgielowee2016;

/**
 * Created by Jasper on 18-8-2016.
 */
public class Highscore {
    private int highscore;
    private String nickname;

    Highscore(int highscore, String nickname){
        this.highscore = highscore;
        this.nickname = nickname;
    }

    public int gethighscore(){
        return highscore;
    }

    public String getName(){
        return nickname;
    }
}