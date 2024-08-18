package org.security.auth_service.model;

import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    private int id ;
    private String name ;
    private int marks ;


}
