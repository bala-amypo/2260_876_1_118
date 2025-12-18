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
    public String getsupplierCode(){
        return supplierCode;
    }
    public void setsupplierCode(String supplierCode){
        this.supplierCode=supplierCode;
    }
    public String getsupplierName(){
        return supplierName;
    }
    public void setsupplierNamee(String supplierName){
        this.supplierName=supplierName;
    }

    public String getemail(){
        return email;
    }
    public void setemail(String stuemail){
        this.email=email;
    }
    public String getphone(){
        return email;
    }
    public void setphone(String phone){
        this.phone=phone;
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
