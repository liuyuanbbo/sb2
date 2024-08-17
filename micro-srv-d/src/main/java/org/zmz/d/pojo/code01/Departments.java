package org.zmz.d.pojo.code01;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Zmz
 */
@Getter
@Setter
@ToString
public class Departments {
    private Integer departmentId;

    private String departmentName;

    private Integer managerId;

    private Integer locationId;
}