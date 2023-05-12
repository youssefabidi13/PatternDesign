package com.youssefabidi.ecommerce.builder;

import com.youssefabidi.ecommerce.entity.Authority;
import com.youssefabidi.ecommerce.entity.Customer;
import com.youssefabidi.ecommerce.entity.Order;

import java.util.HashSet;
import java.util.Set;

public class Builder implements BuilderI {
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private String phoneNumber;
    private String profilePhoto;
    private String password;
    private Set<Authority> authorities;
    private Set<Order> orders;

    public Builder() {
        this.authorities = new HashSet<>();
        this.orders = new HashSet<>();
    }

    @Override
    public BuilderI firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Override
    public BuilderI lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public BuilderI email(String email) {
        this.email = email;
        return this;
    }

    @Override
    public BuilderI bio(String bio) {
        this.bio = bio;
        return this;
    }

    @Override
    public BuilderI phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public BuilderI profilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
        return this;
    }

    @Override
    public BuilderI password(String password) {
        this.password = password;
        return this;
    }

    @Override
    public BuilderI authorities(Set<Authority> authorities) {
        this.authorities = authorities;
        return this;
    }

    @Override
    public BuilderI orders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }

    @Override
    public Customer build() {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setBio(bio);
        customer.setPhoneNumber(phoneNumber);
        customer.setProfilePhoto(profilePhoto);
        customer.setPassword(password);
        customer.setAuthorities(authorities);
        customer.setOrders(orders);
        return customer;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    public String getPassword() {
        return password;
    }



    public Set<Authority> getAuthorities() {
        return authorities;
    }

}
