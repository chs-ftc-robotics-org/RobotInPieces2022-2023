package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TeleOp: Test", group="Tests")
public class TeleOpTest extends LinearOpMode {

    //Declaring motors here
    DcMotor left;
    DcMotor right;

    //When the "INIT" button is pressed:
    @Override
    public void runOpMode() {
        //Get motors from Hardware Map
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "front_right");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "back_left");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "back_right");

        //Wait here until the "START" button is pressed
        waitForStart();

        double forward;
        //Loop this until the "STOP" button is pressed
        while (opModeIsActive()) {
            forward = -gamepad1.left_stick_y;
            if (forward > 0) {
                if (forward < 0.5) { // (0.5,1]
                    frontLeft.setPower(forward);
                    frontRight.setPower(forward);
                    backLeft.setPower(forward);
                    backRight.setPower(forward);
                } else {
                    frontLeft.setPower(0.5);
                    frontRight.setPower(0.5);
                    backLeft.setPower(0.5);
                    backRight.setPower(0.5);
                }
            } else if (forward < 0) {
                if (forward < -0.5) { // (-0.5,-1]
                    frontLeft.setPower(-0.5);
                    frontRight.setPower(-0.5);
                    backLeft.setPower(-0.5);
                    backRight.setPower(-0.5);
                } else {
                    frontLeft.setPower(forward);
                    frontRight.setPower(forward);
                    backLeft.setPower(forward);
                    backRight.setPower(forward);
                }
            }
        }
    }
}