package org.firstinspires.ftc.teamcode.hermes_helper.util;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MechanumDrive {
    private final DcMotorEx fL;
    private final DcMotorEx fR;
    private final DcMotorEx bL;
    private final DcMotorEx bR;
    private final Gamepad gamepad1;
    private final Gamepad gamepad2;
    private final IMU imu;
    private final Telemetry telemetry;

    public MechanumDrive(DcMotorEx fL, DcMotorEx fR, DcMotorEx bL, DcMotorEx bR, IMU imu, Gamepad gamepad1, Gamepad gamepad2, Telemetry telemetry) {
        this.fL = fL;
        this.fR = fR;
        this.bL = bL;
        this.bR = bR;
        this.imu = imu;
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        this.telemetry = telemetry;
    }

    public void drive(double x, double y, double rx){
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        fL.setPower((y + x + rx) / denominator);
        bL.setPower((y - x + rx) / denominator);
        fR.setPower((y - x - rx) / denominator);
        bR.setPower((y + x - rx) / denominator);
    }

    public void drive(double x, double y){
        fL.setPower(y + x);
        fR.setPower(y - x);
        bL.setPower(-y - x);
        bR.setPower(-y + x);
    }

    public void drive(){
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double rx = gamepad1.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        fL.setPower((y + x + rx) / denominator);
        bL.setPower((y - x + rx) / denominator);
        fR.setPower((y - x - rx) / denominator);
        bR.setPower((y + x - rx) / denominator);
    }

    public void fieldCentricDrive(double x, double y, double rx) {
        double botHeading = -imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        telemetry.addData("bot heading: ", botHeading);
        telemetry.update();

        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX *= 1.1;

        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        fL.setPower((rotY + rotX + rx) / denominator);
        bL.setPower((rotY - rotX + rx) / denominator);
        fR.setPower((rotY - rotX - rx) / denominator);
        bR.setPower((rotY + rotX - rx) / denominator);
    }

    public void fieldCentricDrive() {
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double rx = gamepad1.right_stick_x;

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX *= 1.1;

        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        fL.setPower((rotY + rotX + rx) / denominator);
        bL.setPower((rotY - rotX + rx) / denominator);
        fR.setPower((rotY - rotX - rx) / denominator);
        bR.setPower((rotY + rotX - rx) / denominator);
    }
}