package entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
public class LoginAttempt {
    private AtomicInteger countOfAttempts = new AtomicInteger(0);
    private LocalDateTime lastAttemptsTime = LocalDateTime.now();


    public void setCountOfAttempts(AtomicInteger countOfAttempts) {
        this.countOfAttempts = countOfAttempts;
    }

    public void incrementAttempts() {
        countOfAttempts.incrementAndGet();
        lastAttemptsTime = LocalDateTime.now();
    }

    public boolean isBlocked() {
        return countOfAttempts.get() >= 1 && lastAttemptsTime.isBefore(LocalDateTime.now().plus(5, ChronoUnit.MINUTES));
    }

    public  boolean isBlockedExpired() {
        return LocalDateTime.now().isAfter(lastAttemptsTime.plus(5, ChronoUnit.MINUTES));
    }


}
