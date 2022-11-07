package org.RIP.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.RIP.OurRobot;
import org.RIP.Subsystem;

public class Drivetrain implements Subsystem {
    @Override
    public void initialize(LinearOpMode opMode, OurRobot robot) {
        //Get motors from Hardware Map
        DcMotor frontLeft = opMode.hardwareMap.get(DcMotor.class, "front_left");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        DcMotor frontRight = opMode.hardwareMap.get(DcMotor.class, "front_right");
        DcMotor backLeft = opMode.hardwareMap.get(DcMotor.class, "back_left");
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        DcMotor backRight = opMode.hardwareMap.get(DcMotor.class, "back_right");
    }
}
