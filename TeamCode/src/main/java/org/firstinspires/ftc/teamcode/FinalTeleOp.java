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
            double x;
            double y;
            double y1;
            double x1;
            double strife;
            //Loop this until the "STOP" button is pressed
            boolean aPressed = false;
            while (opModeIsActive()) {
                // Forward & Backward
                y = (-gamepad1.left_stick_y) * 0.5;
                x1 = -(gamepad1.left_stick_x) * 0.5;
                x = (-gamepad1.right_stick_x) * 0.5;

                if(gamepad2.a) {
                    if(!aPressed) {
                        if (robot.slides.isClawLocked()) {
                            robot.slides.unlockClaw();
                        } else {
                            robot.slides.lockClaw();
                        }
                    }
                    aPressed = true;
                } else {
                    aPressed = false;
                }

                robot.drivetrain.frontLeft.setPower((y - x) - x1);
                robot.drivetrain.backLeft.setPower((y - x) + x1);
                robot.drivetrain.frontRight.setPower((y + x) + x1);
                robot.drivetrain.backRight.setPower((y + x) - x1);

                String unlockedText = robot.slides.isClawLocked() ? "Locked" : "Unlocked";
                telemetry.addLine("Claw: " + unlockedText);
                telemetry.addLine("Slides: " + robot.slides.percentToTop() + "%");

            }
        }
    }
}