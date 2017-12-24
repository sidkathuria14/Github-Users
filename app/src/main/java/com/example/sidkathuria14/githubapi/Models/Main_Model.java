package com.example.sidkathuria14.githubapi.Models;

/**
 * Created by sidkathuria14 on 25/12/17.
 */

public class Main_Model {
    int public_repos,private_repos,followers,following,public_gists;
String type,bio;

    public int getPublic_repos() {
        return public_repos;
    }

    public int getPrivate_repos() {
        return private_repos;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public int getPublic_gists() {
        return public_gists;
    }

    public String getType() {
        return type;
    }

    public String getBio() {
        return bio;
    }

    public Main_Model(int public_repos, int private_repos, int followers, int following, int public_gists, String type, String bio) {
        this.public_repos = public_repos;
        this.private_repos = private_repos;
        this.followers = followers;
        this.following = following;
        this.public_gists = public_gists;
        this.type = type;
        this.bio = bio;
    }
}
