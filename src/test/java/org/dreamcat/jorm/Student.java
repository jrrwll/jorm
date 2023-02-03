package org.dreamcat.jorm;

import java.util.Date;
import lombok.Data;

/**
 * @author Jerry Will
 * @version 2023-01-24
 */
@Data
public class Student {

    private Long id;
    private String name;
    private Date birthday;
}
