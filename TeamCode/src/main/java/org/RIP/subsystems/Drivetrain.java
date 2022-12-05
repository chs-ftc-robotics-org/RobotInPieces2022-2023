package org.RIP.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.RIP.OurRobot;
import org.RIP.Subsystem;

import java.util.Objects;

public class Drivetrain extends Subsystem {
    private boolean isActive = false;

    private double frontLeftPwr;
    private double frontRightPwr;
    private double backLeftPwr;
    private double backRightPwr;

    private LinearOpMode opMode;

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor[] driveMotors = {frontLeft, frontRight, backLeft, backRight};

    private ElapsedTime globalTimer;

    @Override
    public void initialize(LinearOpMode opMode, ElapsedTime globalTimer) {
        this.opMode = opMode;
        //Get motors from Hardware Map
        frontLeft = opMode.hardwareMap.get(DcMotor.class, "front_left");
        frontRight = opMode.hardwareMap.get(DcMotor.class, "front_right");
        backLeft = opMode.hardwareMap.get(DcMotor.class, "back_left");
        backRight = opMode.hardwareMap.get(DcMotor.class, "back_right");


        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        isActive = true;
    }

    @Override
    public void update() {
        if(!isActive) return;
        frontLeft.setPower(frontLeftPwr);
        frontRight.setPower(frontRightPwr);
        backLeft.setPower(backLeftPwr);
        backRight.setPower(backRightPwr);
    }

    @Override
    public void stop() {
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
    public void move(double power, double miliseconds) {
        if(!isActive) return;

        double endTime  = timer.milliseconds() ;
        opMode.telemetry.addLine("second : "+String.valueOf(miliseconds));
        opMode.telemetry.addLine("Eval : "+String.valueOf(endTime-startTime));

        opMode.telemetry.update();

        if((endTime - startTime) < miliseconds-2000) {
            frontLeftPwr = power;
            frontRightPwr = power;
            backLeftPwr = power;
            backRightPwr = power;
        } else if ((endTime - startTime) < miliseconds+5000){
            frontLeftPwr = power;
            frontRightPwr = -power;
            backLeftPwr = power;
            backRightPwr = -power;
        } else {
            stop();
        }
    }
}
