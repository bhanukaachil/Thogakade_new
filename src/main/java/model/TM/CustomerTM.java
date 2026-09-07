package model.TM;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class CustomerTM {

    private String id;
    private String name;
    private String title;
    private String address;
    private Date dob;
    private double salary;
    private String city;
    private String province;
    private String postalcode;

    public CustomerTM(String id, String title, String name, Date dob, double salary, String address, String city, String province, String postalcode) {
        this.id = id;
        this.title = title;
        this.name = title+" "+name;
        this.address = address;
        this.dob = dob;
        this.salary = salary;
        this.city = city+" "+postalcode  ;
        this.province = province;
        this.postalcode = postalcode;
    }


}
