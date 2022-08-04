/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Dell
 */
public class HistoryManage {
    private String time;
    private String name;
    private String content;

    public HistoryManage() {
    }

    public HistoryManage(String time, String name, String content) {
        this.time = time;
        this.name = name;
        this.content = content;
    }

    public HistoryManage(String name, String content) {
        this.name = name;
        this.content = content;
    }
    

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
