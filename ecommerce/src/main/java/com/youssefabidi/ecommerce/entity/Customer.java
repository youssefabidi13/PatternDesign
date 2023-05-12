    package com.youssefabidi.ecommerce.entity;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import com.youssefabidi.ecommerce.iterator.IContainer;
    import com.youssefabidi.ecommerce.iterator.IIterator;
    import com.youssefabidi.ecommerce.iterator.OrderIterator;
    import jakarta.persistence.*;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

    import java.util.HashSet;
    import java.util.Set;

    @Entity
    @Table(name="customer")
    public class Customer implements IContainer{

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
        @JsonManagedReference
        private Set<Order> orders = new HashSet<>();


        public void setPassword(String password) {
            this.password = password;
            this.encryptedPassword =
                    //password;
                    new BCryptPasswordEncoder().encode(password);
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

        public Long getId() {
            return id;
        }

        @Override
        public IIterator createIterator() {
            return new OrderIterator(orders);
        }

        public Set<Order> getOrders() {
            return orders;
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
