    package com.youssefabidi.ecommerce.entity;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;

    import java.util.HashSet;
    import java.util.Set;

    @Entity
    @Table(name="customer")
    public class Customer {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private Long id;

        @Column(name="first_name")
        private String firstName;

        @Column(name="last_name")
        private String lastName;

        @JsonIgnore
        @OneToMany(mappedBy = "customer",fetch=FetchType.EAGER)
        private Set<Authority> authorities;
        @Column(name="email")
        private String email;

        @Column(name="bio")
        private String bio;

        @Column(name="phonenumber")
        private String phoneNumber;

        @Column(name="password")
        @Transient
        private String password;

        @Column(name="encrypted_password")
        private String encryptedPassword;

        @Column(name="profile_photo")
        private String profilePhoto;

        @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
        private Set<Order> orders = new HashSet<>();


        public void setPassword(String password) {
            this.password = password;
            this.encryptedPassword = password;
                    //new BCryptPasswordEncoder().encode(password);
        }

        public String getEncryptedPassword() {
            return encryptedPassword;
        }
        public Customer() {}





        public Set<Authority> getAuthorities() {
            return authorities;
        }

        public void setAuthorities(Set<Authority> authorities) {
            this.authorities = authorities;
        }

        public static class Builder {
            //private Long id;
            private String firstName;
            private String lastName;
            private String email;
            private String bio;
            private String phoneNumber;
            private String profilePhoto;

            private String password;

            private Set<Order> orders;
            private String role;

            private Set<Authority> authorities;

            public Builder() {}



            public Builder password(String password) {
                this.password = password;
                return this;
            }
            public Builder firstName(String firstName) {
                this.firstName = firstName;
                return this;
            }

            public Builder lastName(String lastName) {
                this.lastName = lastName;
                return this;
            }

            public Builder email(String email) {
                this.email = email;
                return this;
            }

            public Builder bio(String bio) {
                this.bio = bio;
                return this;
            }

            public Builder phoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
                return this;
            }

            public Builder profilePhoto(String profilePhoto) {
                this.profilePhoto = profilePhoto;
                return this;
            }

            public Builder orders(Set<Order> orders) {
                this.orders = orders;
                return this;
            }


            public Builder authorities(Set<Authority> authorities) {
                this.authorities = authorities;
                        //new HashSet<>(authorities);
                //this.authorities.add(new Authority("ROLE_USER",this.build()));
                return this;
            }


            public Customer build() {
                Customer customer = new Customer();
               // customer.setId(id);
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setEmail(email);
                customer.setBio(bio);
                customer.setPhoneNumber(phoneNumber);
                customer.setProfilePhoto(profilePhoto);
                customer.setOrders(orders);
                customer.setPassword(password);
                customer.setAuthorities(authorities);
                return customer;
            }

            public String getEmail() {
                return email;
            }

            public String getFirstName() {
                return firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public String getBio() {
                return bio;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public String getProfilePhoto() {
                return profilePhoto;
            }

            public String getPassword() {
                return password;
            }

            public Set<Order> getOrders() {
                return orders;
            }

            public Set<Authority> getAuthorities() {
                return authorities;
            }
        }


        public void add(Order order){
            if(order != null){
                if (orders == null) {
                    orders = new HashSet<>();
                }
                orders.add(order);
                order.setCustomer(this);
            }
        }

        public void remove(Order order){
            if(order != null){
                orders.remove(order);
                order.setCustomer(null);
            }
        }


        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }



        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }



        public void setBio(String bio) {
            this.bio = bio;
        }



        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }



        public void setProfilePhoto(String profilePhoto) {
            this.profilePhoto = profilePhoto;
        }



        public void setOrders(Set<Order> orders) {
            this.orders = orders;
        }
    }
