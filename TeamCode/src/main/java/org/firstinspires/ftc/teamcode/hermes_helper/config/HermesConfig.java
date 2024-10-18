package org.firstinspires.ftc.teamcode.hermes_helper.config;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

@Config("IMU")
public final class HermesConfig {

    public static class IMU{
        public static final RevHubOrientationOnRobot.LogoFacingDirection LOGO_FACING_DIRECTION =
                RevHubOrientationOnRobot.LogoFacingDirection.UP;

        public static final RevHubOrientationOnRobot.UsbFacingDirection USB_FACING_DIRECTION =
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
    }
}
