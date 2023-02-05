package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.RIP.OurRobot;

@TeleOp(name="Final TeleOp")
public class FinalTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        //Get motors from Hardware Map
        OurRobot robot = new OurRobot();
        robot.initialize(this);
        waitForStart();

        double slidePower;
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
                y = (-gamepad1.left_stick_y) * 0.4;
                x1 = -(gamepad1.left_stick_x) * 0.5;
                x = (-gamepad1.right_stick_x) * 0.4

                ;

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

//                if(gamepad2.dpad_up) {
//                    slidePower = 1.0;
//                } else if (gamepad2.dpad_down) {
//                    slidePower = -1.0;
//                } else {
//                    slidePower = 0.0;
//                }
//                robot.slides.slideLeft.setPower(slidePower);
//                robot.slides.slideRight.setPower(slidePower);
                if(gamepad2.left_trigger > 0.2) {
                    robot.slides.lower(gamepad2.left_trigger);
                } else if(gamepad2.right_trigger > 0.2 ) {
                    robot.slides.raise(gamepad2.right_trigger);
                } else {
                    robot.slides.stop();
                }


                String unlockedText = robot.slides.isClawLocked() ? "Locked" : "Unlocked";
                telemetry.clear();
                telemetry.addLine("Claw: " + unlockedText);
                telemetry.addLine("Slides: " + robot.slides.percentToTop() + "%");
                telemetry.update();

            }
        }
    }
}