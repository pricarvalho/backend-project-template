package com.company.app.common;

import lombok.Data;

@Data
public class RestDataResponse<T> {

    private final T data;

    public RestDataResponse(T data) {
		this.data = data;
    }
    
}
