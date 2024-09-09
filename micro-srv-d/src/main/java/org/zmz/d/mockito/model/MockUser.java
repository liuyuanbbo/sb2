package org.zmz.d.mockito.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Zmz
 */
@Getter
@Setter
@NoArgsConstructor
public class MockUser {
    private Long id;

    private String name;

    private int age;

    public MockUser(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
