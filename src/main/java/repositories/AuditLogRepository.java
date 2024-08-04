package repositories;

import models.AuditLog;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AuditLogRepository {

    private List<AuditLog> auditLogs;

    public AuditLogRepository() {
        this.auditLogs = new ArrayList<>();
    }

    public void addAuditLog(AuditLog auditLog) {
        auditLogs.add(auditLog);
    }

    public Optional<AuditLog> getAuditLogById(int id) {
        return auditLogs.stream().filter(log -> log.getId() == id).findFirst();
    }

    public List<AuditLog> getAllAuditLogs() {
        return new ArrayList<>(auditLogs);
    }

    public List<AuditLog> getAuditLogsByUsername(String username) {
        return auditLogs.stream()
                .filter(log -> log.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public List<AuditLog> getAuditLogsByAction(String action) {
        return auditLogs.stream()
                .filter(log -> log.getAction().equals(action))
                .collect(Collectors.toList());
    }

    public List<AuditLog> getAuditLogsByDate(LocalDateTime date) {
        return auditLogs.stream()
                .filter(log -> log.getTimestamp().toLocalDate().equals(date.toLocalDate()))
                .collect(Collectors.toList());
    }

    public boolean deleteAuditLog(int id) {
        return auditLogs.removeIf(log -> log.getId() == id);
    }
}
