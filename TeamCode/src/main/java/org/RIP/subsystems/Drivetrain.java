package org.RIP.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.RIP.OurRobot;
import org.RIP.Subsystem;

import java.io.IOException;
import java.util.Objects;

public class Drivetrain extends Subsystem {

    private double frontLeftPwr;
    private double frontRightPwr;
    private double backLeftPwr;
    private double backRightPwr;

    private boolean isActive;

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    private LinearOpMode opMode;
    private ElapsedTime globalTimer;

    @Override
    public void disable() {
        isActive = false;
        stopMoving();
    }

    @Override
    public void initialize(LinearOpMode opMode, ElapsedTime globalTimer) throws IOException {
        this.opMode = opMode;
        this.globalTimer = globalTimer;
        //Get motors from Hardware Map
        frontLeft = opMode.hardwareMap.get(DcMotor.class, "front_left");
        frontRight = opMode.hardwareMap.get(DcMotor.class, "front_right");
        backLeft = opMode.hardwareMap.get(DcMotor.class, "back_left");
        backRight = opMode.hardwareMap.get(DcMotor.class, "back_right");


        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void update() {
        if(!isActive) return;
        frontLeft.setPower(frontLeftPwr);
        frontRight.setPower(frontRightPwr);
        backLeft.setPower(backLeftPwr);
        backRight.setPower(backRightPwr);
    }
    public void stopMoving() {
        frontLeftPwr=0;
        frontRightPwr=0;
        backLeftPwr=0;
        backRightPwr=0;
    }

    @Override
    public String toString() {
        return "drivetrain";
    }

    //move (blocking)
    /** Positive values moves forward, negative moves back */
    public void move(double power) {
        ElapsedTime timer = new ElapsedTime();
        frontLeftPwr = power;
        frontRightPwr = power;
        backLeftPwr = power;
        backRightPwr = power;
    }
    /** Positive values turn right, negative turns left */
    public void rotate(double power) {
        frontLeftPwr = power;
        frontRightPwr = -power;
        backLeftPwr = power;
        backRightPwr = -power;
    }
    /** Positive values strafe right, negative strafes left */
    public void strafe(double power){
        frontLeftPwr = power;
        frontRightPwr = -power;
        backLeftPwr = -power;
        backRightPwr = power;
    }
    ElapsedTime timer = new ElapsedTime();
    public void moveWithTime(double power, double milliseconds) {
        //if(!isActive) return;
        timer.reset();
        //TODO: make this only run once
        double startTime = timer.milliseconds();
        double endTime  = timer.milliseconds() ;
        opMode.telemetry.addLine("second : "+String.valueOf(milliseconds));
        opMode.telemetry.addLine("Eval : "+String.valueOf(endTime-startTime));

        opMode.telemetry.update();

        if((endTime - startTime) < milliseconds-2000) {
            frontLeftPwr = power;
            frontRightPwr = power;
            backLeftPwr = power;
            backRightPwr = power;
        } else if ((endTime - startTime) < milliseconds+5000){
            frontLeftPwr = power;
            frontRightPwr = -power;
            backLeftPwr = power;
            backRightPwr = -power;
        } else {
            stopMoving();
        }
    }
}
