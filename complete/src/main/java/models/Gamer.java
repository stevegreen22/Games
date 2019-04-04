package models;

import com.omertron.bgg.model.UserInfo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Entity
public class Gamer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private List<Game>excited = new ArrayList<>();
    private List<Game>wants = new ArrayList<>();
    private List<Game>likes = new ArrayList<>();
    private List<Game>fuckno = new ArrayList<>();

    public UserInfo getBggUserinfo() {
        return bggUserinfo;
    }

    public void setBggUserinfo(UserInfo bggUserinfo) {
        this.bggUserinfo = bggUserinfo;
    }

    private UserInfo bggUserinfo = null;

    public List<Game> getExcited() {
        return excited;
    }

    public void setExcited(List<Game> excited) {
        this.excited = excited;
    }

    public List<Game> getWants() {
        return wants;
    }

    public void setWants(List<Game> wants) {
        this.wants = wants;
    }

    public List<Game> getLikes() {
        return likes;
    }

    public void setLikes(List<Game> likes) {
        this.likes = likes;
    }

    public List<Game> getFuckno() {
        return fuckno;
    }

    public void setFuckno(List<Game> fuckno) {
        this.fuckno = fuckno;
    }

    private final String prefix = "C:\\Users\\Steve\\Downloads\\gs-spring-boot-master\\gs-spring-boot-master\\complete\\src\\main\\";

    public Gamer(String name){
        this.name = name;
        CreateGamingLists();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void CreateGamingLists() {
        CreateExcited(prefix + this.name+"_excited");
        CreateWants(prefix + this.name+"_wants");
        CreateLikes(prefix + this.name+"_likes");
        CreateFuckNo(prefix + this.name+"_fuckno");
    }

    private void CreateExcited(String s) {
        UpdateList(s, excited);
    }
    private void CreateWants(String s) {
        UpdateList(s, wants);
    }
    private void CreateLikes(String s) {
        UpdateList(s, likes);
    }
    private void CreateFuckNo(String s) {
        UpdateList(s, fuckno);
    }

    private void UpdateList(String filename, List<Game>list){
        try {
            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNextLine()){
                list.add(new Game(scan.nextLine()));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
