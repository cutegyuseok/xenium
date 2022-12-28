package com.example.xenium.member.dto;

public class Order {
    private int preid;
    private String id;
    private String name;
    private String phone;
    private String address;
    private String addressDetail;
    private String postcode;

    public Order(SignUpDTO dto) {
        id = dto.getId();
        name = dto.getName();
        phone = dto.getPhone();
        address = dto.getAddress();
        addressDetail = dto.getAddressDetail();
        postcode = dto.getPostcode();
    }

    public int getPreid() {
        return preid;
    }

    public void setPreid(int preid) {
        this.preid = preid;
    }
}
