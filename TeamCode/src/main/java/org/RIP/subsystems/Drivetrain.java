package org.RIP.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.RIP.OurRobot;
import org.RIP.Subsystem;

public class Drivetrain implements Subsystem {
    private boolean isActive = false;

    private double frontLeftPwr;
    private double frontRightPwr;
    private double backLeftPwr;
    private double backRightPwr;

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    @Override
    public void initialize(LinearOpMode opMode, OurRobot robot, ElapsedTime globalTimer) {
        //Get motors from Hardware Map
        frontLeft = opMode.hardwareMap.get(DcMotor.class, "front_left");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight = opMode.hardwareMap.get(DcMotor.class, "front_right");
        backLeft = opMode.hardwareMap.get(DcMotor.class, "back_left");
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight = opMode.hardwareMap.get(DcMotor.class, "back_right");
    }

    @Override
    public void tick() {
        if(!isActive) return;
        frontLeft.setPower(frontLeftPwr);
        frontRight.setPower(frontRightPwr);
        backLeft.setPower(backLeftPwr);
        backRight.setPower(backRightPwr);
    }

    @Override
    public void stop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    @Override
    public void disable() {
        isActive = false;
    }

    @Override
    public boolean active() {
        return isActive;
    }

    @Override
    public String toString() {
        return "drivetrain";
    }

    public void move(double power, double seconds) {
        if(!isActive) return;
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        double startTime = timer.milliseconds();
    }
}
