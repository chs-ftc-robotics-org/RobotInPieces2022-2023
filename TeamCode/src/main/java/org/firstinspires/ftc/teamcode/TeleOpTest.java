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
        //frontLeft.setDirection(DcMotor.Direction.REVERSE);
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "front_right");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "back_left");
        //backLeft.setDirection(DcMotor.Direction.REVERSE);
        DcMotor backRight = hardwareMap.get(DcMotor.class, "back_right");

        //Wait here until the "START" button is pressed
        waitForStart();

        double x;
        double y;
        double strife;
        //Loop this until the "STOP" button is pressed
        while (opModeIsActive()) {
            // Forward & Backward
            y = (-gamepad1.left_stick_y) * 0.75;
            x=  (-gamepad1.left_stick_x) * 0.75;

            frontLeft.setPower(y);
            frontRight.setPower(-y);
            backLeft.setPower(-y);
            backRight.setPower(y);

            if (x > 0) {

            }

            /*
            // Strife Left & Right
            strife = (-gamepad2.right_stick_x) * 0.5;
            if (strife > 0) { // Strife Right
                frontLeft.setPower(forward);
                frontRight.setPower(-forward);
                backLeft.setPower(-forward);
                backRight.setPower(forward);
            } else { // Strife Left
                frontLeft.setPower(-forward);
                frontRight.setPower(forward);
                backLeft.setPower(-forward);
                backRight.setPower(forward);
            }
             */

        }
    }
}