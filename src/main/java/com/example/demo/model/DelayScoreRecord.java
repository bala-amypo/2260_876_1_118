package com.example.demo.model;
public class DelayScoreRecord {
    @Id
    private long id;
    private String supplierCode;
    private String supplierName;
    private String email;
    private String phone;
    private boolean active;
    
    public long getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String supplierCode(){
        return supplierCode;
    }
    public void supplierCode(String supplierCode){
        this.supplierCode=supplierCode;
    }
    public String supplierCode(){
        return supplierCode;
    }
    public void supplierCode(String supplierCode){
        this.supplierCode=supplierCode;
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
