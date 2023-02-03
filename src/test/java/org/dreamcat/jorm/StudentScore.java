package org.dreamcat.jorm;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @author Jerry Will
 * @version 2023-01-24
 */
@Data
public class StudentScore {

    private Long id;
    private Long studentId;
    private String subject;
    private BigDecimal score;
    private Date time;
}
