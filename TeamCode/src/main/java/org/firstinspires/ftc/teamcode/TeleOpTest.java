package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "front_right");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        DcMotor backRight = hardwareMap.get(DcMotor.class, "back_right");

        //Wait here until the "START" button is pressed
        waitForStart();

        double x;
        double y;
        double y1;
        double x1;
        double strife;
        //Loop this until the "STOP" button is pressed
        while (opModeIsActive()) {
            // Forward & Backward
            y = (-gamepad1.left_stick_y) * 0.5;
            x = (gamepad1.left_stick_x) * 0.5;
            y1 = (-gamepad1.right_stick_y) * 0.5;
            x1 = (-gamepad1.right_stick_x) * 0.5;

            frontLeft.setPower((y-x)-x1);
            backLeft.setPower((y-x)+x1);
            frontRight.setPower((y+x)+x1);
            backRight.setPower((y+x)-x1);


        }
    }
}