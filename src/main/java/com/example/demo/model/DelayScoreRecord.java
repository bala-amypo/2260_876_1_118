package com.example.demo.model;
public class DelayScoreRecord {
    private Long id;
    private String stuname;
    private String stuemail;
    private float cgpa;
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getStuname(){
        return stuname;
    }
    public void setStuname(String stuname){
        this.stuname=stuname;
    }
    public String getStuemail(){
        return stuemail;
    }
    public void setStuemail(String stuemail){
        this.stuemail=stuemail;
    }
    public float cgpa(){
        return cgpa;
    }
    public void setCgpa(float cgpa){
        this.cgpa=cgpa;
    }

    public Student(int id,String stuname,String stuemail,float cgpa){
        this.id=id;
        this.stuname=stuname;
        this.stuemail=stuemail;
        this.cgpa=cgpa;
    }
    public Student(){

    }

}
