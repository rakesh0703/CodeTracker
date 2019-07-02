package com.example.rakesh.contest_overflow;

public class contest_items {
    private String contestname;
    private String startdate;
    private String enddate;
    private String contestlogo;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    private String link;

    public void setContestname(String contestname) {
        this.contestname = contestname;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getContestname() {

        return contestname;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public contest_items(String contestname, String startdate, String enddate,String contestlogo,String link) {

        this.contestname = contestname;
        this.startdate = startdate;
        this.enddate = enddate;
        this.contestlogo = contestlogo;
        this.link = link;
    }

    public String getContestlogo() {
        return contestlogo;
    }

    public void setContestlogo(String contestlogo) {
        this.contestlogo = contestlogo;
    }
}
