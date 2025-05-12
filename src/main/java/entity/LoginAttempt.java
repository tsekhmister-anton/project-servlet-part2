package entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
public class LoginAttempt {
    private Integer countOfAttempts = 0;
    private LocalDateTime lastAttemptsTime = LocalDateTime.now();


    public Integer getCountOfAttempts() {
        return countOfAttempts;
    }

    public LocalDateTime getLastAttemptsTime() {
        return lastAttemptsTime;
    }

    public void setCountOfAttempts(Integer countOfAttempts) {
        this.countOfAttempts = countOfAttempts;
    }

    public void incrementAttempts() {
        countOfAttempts++;
        lastAttemptsTime = LocalDateTime.now();
    }

    public boolean isBlocked() {
        return countOfAttempts >= 1 && lastAttemptsTime.isBefore(LocalDateTime.now().plus(5, ChronoUnit.MINUTES));
    }

    public  boolean isBlockedExpired() {
        return LocalDateTime.now().isAfter(lastAttemptsTime.plus(5, ChronoUnit.MINUTES));
    }


}
