package com.prabu.serviceapi.customer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractRestController {

    public static String asJsonString(final Object object){
        try{
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
