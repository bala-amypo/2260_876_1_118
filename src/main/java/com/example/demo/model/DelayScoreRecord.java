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
    public String getSupplierCode(){
        return supplierCode;
    }
    public void setSupplierCode(String supplierCode){
        this.supplierCode=supplierCode;
    }
    public String getSupplierName(){
        return supplierName;
    }
    public void setSupplierNamee(String supplierName){
        this.supplierName=supplierName;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String stuemail){
        this.email=email;
    }
    public String getPhone(){
        return email;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }

    public boolean getActive(){
        return active;
    }
    public void setActive(boolean active){
        this.active=active;
    }

    public DelayScoreRecord(long id,String supplierCode, String supplierName, String email, String phone, boolean active;){
        this.id=id;
        this.stuname=stuname;
        this.stuemail=stuemail;
        this.cgpa=cgpa;
    }
    public DelayScoreRecord(){

    }

}
