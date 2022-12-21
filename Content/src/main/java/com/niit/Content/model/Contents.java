package com.niit.Content.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Contents {

    @Id
    private int id;
    private String contentname;
    private String contenttittle;

}
