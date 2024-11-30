package com.register.dto;

import java.io.Serializable;

public record CustomerRecord(String userId,String userName, String email,long phoneNo, String address)implements Serializable {

}
