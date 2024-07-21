package com.rupeex.trading.services.implementations;

import com.rupeex.trading.entities.TwoFactorOTP;
import com.rupeex.trading.entities.User;
import com.rupeex.trading.services.TwoFactorOTPService;
import org.springframework.stereotype.Service;

@Service
public class TwoFactorOtpImplementation implements TwoFactorOTPService {


    @Override
    public TwoFactorOTP createTwoFactorOTP(User user, String otp, String jwt) {
        return null;
    }

    @Override
    public TwoFactorOTP findByUser(Long useId) {
        return null;
    }

    @Override
    public TwoFactorOTP findById(String id) {
        return null;
    }

    @Override
    public TwoFactorOTP verifyTwoFactorOtp(TwoFactorOTP twoFactorOTP, String otp) {
        return null;
    }

    @Override
    public void deleteTwoFactorOtp(TwoFactorOTP twoFactorOTP) {

    }
}
