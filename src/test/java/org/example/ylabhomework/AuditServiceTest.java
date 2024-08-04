package org.example.ylabhomework;

import models.AuditLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.AuditLogRepository;
import services.AuditService;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AuditServiceTest {

    private AuditService auditService;

    @BeforeEach
    public void setUp() {
        AuditLogRepository auditLogRepository = new AuditLogRepository();
        auditService = new AuditService(auditLogRepository);
    }

    @Test
    public void testLogAction() {
        auditService.logAction("Test Action", "test_user");
        List<AuditLog> logs = auditService.getAllLogs();
        assertEquals(1, logs.size());
        AuditLog log = logs.get(0);
        assertEquals("Test Action", log.getAction());
        assertEquals("test_user", log.getUsername());
    }

    @Test
    public void testGetAllLogs() {
        auditService.logAction("Action 1", "user1");
        auditService.logAction("Action 2", "user2");
        List<AuditLog> logs = auditService.getAllLogs();
        assertEquals(2, logs.size());
    }

    @Test
    public void testExportLogs() {
        auditService.logAction("Action to export", "user_export");
        auditService.exportLogs("test_logs.txt");
    }
}
