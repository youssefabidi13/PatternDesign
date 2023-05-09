package com.youssefabidi.ecommerce.dto;

public class CustomerDTO {
    private String bio;
    private String phone;



    public CustomerDTO() {}
    public CustomerDTO(String bio, String phone) {
        this.bio = bio;
        this.phone = phone;
    }



    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}