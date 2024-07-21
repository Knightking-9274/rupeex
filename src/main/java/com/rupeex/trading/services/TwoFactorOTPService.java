package com.rupeex.trading.services;

import com.rupeex.trading.entities.TwoFactorOTP;
import com.rupeex.trading.entities.User;

public interface TwoFactorOTPService {
    TwoFactorOTP createTwoFactorOTP(User user, String otp,String jwt);
    TwoFactorOTP findByUser(Long useId);
    TwoFactorOTP findById(String id);
    TwoFactorOTP verifyTwoFactorOtp(TwoFactorOTP twoFactorOTP,String otp);
    void deleteTwoFactorOtp(TwoFactorOTP twoFactorOTP);
}
