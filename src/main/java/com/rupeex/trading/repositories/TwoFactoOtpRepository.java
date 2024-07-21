package com.rupeex.trading.repositories;

import com.rupeex.trading.entities.TwoFactorOTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwoFactoOtpRepository extends JpaRepository<TwoFactorOTP,String> {
    TwoFactorOTP findByUserId(Long userId);
}

