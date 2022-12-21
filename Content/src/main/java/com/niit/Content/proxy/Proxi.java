package com.niit.Content.proxy;

import com.niit.Content.model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service", url = "localhost:8082")
public interface Proxi {

    @PostMapping("/user/v1/register")
    public ResponseEntity<?> sendUserObjectToAuthApp(@RequestBody UserDTO userDTO);
}
