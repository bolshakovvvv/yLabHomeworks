package models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditLog {
    private int id;
    private String username;
    private String action;
    private LocalDateTime timestamp;

}
