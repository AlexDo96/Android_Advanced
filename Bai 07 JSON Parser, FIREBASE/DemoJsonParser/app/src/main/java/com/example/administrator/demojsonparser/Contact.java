package com.example.administrator.demojsonparser;

/**
 * Created by Administrator on 4/23/2017.
 */
/*
"id": "1",
        "name": "teo",
        "email": "teo@gmail.com",
        "address": "xx-xx-xxxx,x - street, x - country",
        "gender" : "male",
        "phone": {
        "mobile": "+91 0000000000",
        "home": "00 000000",
        "office": "00 000000"
        }
*/

public class Contact {
    String id,name,email,address,gender,mobile,home,office;

    public Contact() {
    }

    public Contact(String id, String name, String email, String address, String gender, String mobile, String home, String office) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.mobile = mobile;
        this.home = home;
        this.office = office;
    }
}
