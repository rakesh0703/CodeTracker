package com.example.rakesh.contest_overflow;

public class todo_items {
    private String contestname;
    private String tags;
    private String date;
    private String Status;

    public todo_items(String contestname, String tags, String date, String status) {
        this.contestname = contestname;
        this.tags = tags;
        this.date = date;
        this.Status = status;
    }

    public String getContestname() {

        return contestname;
    }

    public void setContestname(String contestname) {
        this.contestname = contestname;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
