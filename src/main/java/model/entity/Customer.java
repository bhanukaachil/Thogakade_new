package model.entity;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String id;
    private String title;
    private String name;
    private Date dob;
    private double salary;
    private String address;
    private String city;
    private String province;
    private String postalcode;



}
