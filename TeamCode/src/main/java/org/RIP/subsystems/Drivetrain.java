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

    private LinearOpMode opMode;

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor[] driveMotors = {frontLeft, frontRight, backLeft, backRight};

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
    ElapsedTime timer = new ElapsedTime();
    boolean initialized;
    double startTime = timer.milliseconds();
    public void move(double power, double milliseconds) {
        //if(!isActive) return;

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
