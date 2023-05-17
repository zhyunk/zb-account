package com.zhyun.account.domain;

import com.zhyun.account.exception.AccountException;
import com.zhyun.account.type.AccountStatus;
import com.zhyun.account.type.ErrorCode;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static com.zhyun.account.type.ErrorCode.AMOUNT_EXCEED_BALANCE;
import static com.zhyun.account.type.ErrorCode.INVALID_REQUEST;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity // 일종의 설정 파일 .
public class Account  extends BaseEntity {

    @ManyToOne
    private AccountUser accountUser;
    private String accountNumber;

    @Enumerated(EnumType.STRING) // enum 클래스 값을 String인 이름 자체로 등록함
    private AccountStatus accountStatus;
    private Long balance;

    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;

    public void useBalance(Long amount) {
        if (amount > balance)
            throw new AccountException(AMOUNT_EXCEED_BALANCE);

        balance -= amount;
    }

    public void cancelBalance(Long amount) {
        if (amount < 0)
            throw new AccountException(INVALID_REQUEST);

        balance += amount;
    }
}
