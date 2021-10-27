package org.dreamcat.jorm.exmaple.domain;

import java.util.Date;
import lombok.Data;

/**
 * @author Jerry Will
 * @version 2021-10-27
 */
@Data
public class Permission {

    Long id;
    Date createdAt;
    Date updatedAt;
    String code;
}
