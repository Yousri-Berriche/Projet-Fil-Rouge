import java.time.LocalDateTime;

public interface ILogParser {
    AttackEvent parseLogLine(String logLine);
}