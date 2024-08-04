package services;

import models.AuditLog;
import repositories.AuditLogRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class AuditService {

    private AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void logAction(String action, String username) {
        AuditLog auditLog = new AuditLog();
        auditLog.setAction(action);
        auditLog.setUsername(username);
        auditLog.setTimestamp(LocalDateTime.now());
        auditLogRepository.addAuditLog(auditLog);
    }

    public List<AuditLog> getAllLogs() {
        return auditLogRepository.getAllAuditLogs();
    }

    public void exportLogs(String filename) {
        List<AuditLog> logs = getAllLogs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (AuditLog log : logs) {
                writer.write(log.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
