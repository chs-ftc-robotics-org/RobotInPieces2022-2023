package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.RIP.OurRobot;

@Autonomous(name="_Final TeleOp")
public class FinalTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        //Get motors from Hardware Map
        OurRobot robot = new OurRobot();
        robot.initialize(this);
        waitForStart();
        while(opModeIsActive()) {
            robot.drivetrain.move(0.3, 5000);
            robot.drivetrain.update();
        }
    }
}