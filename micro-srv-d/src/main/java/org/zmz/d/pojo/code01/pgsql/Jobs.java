package org.zmz.d.pojo.code01.pgsql;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Zmz
 */
@Getter
@Setter
@ToString
public class Jobs {
    private String jobId;

    private String jobTitle;

    private Integer minSalary;

    private Integer maxSalary;
}