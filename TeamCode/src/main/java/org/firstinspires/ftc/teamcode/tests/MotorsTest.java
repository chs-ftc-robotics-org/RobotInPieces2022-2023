package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Motors: Test", group="Tests")
public class MotorsTest extends LinearOpMode {

    //Declaring motors here
    DcMotor left;
    DcMotor right;

    //When the "INIT" button is pressed:
    @Override
    public void runOpMode() {
        //Get motors from Hardware Map
        DcMotor m3 = hardwareMap.get(DcMotor.class, "front_left");
        DcMotor m2 = hardwareMap.get(DcMotor.class, "front_right");
        DcMotor m1 = hardwareMap.get(DcMotor.class, "back_left");
        DcMotor m0 = hardwareMap.get(DcMotor.class, "back_right");
        waitForStart();

        while(opModeIsActive()){

            if (gamepad1.left_stick_y > 0.2) {
                m0.setPower(gamepad1.left_stick_y);
            } else if (gamepad1.left_stick_y < -0.2){
                m1.setPower(gamepad1.left_stick_y);
            } else {
                m0.setPower(0);
                m1.setPower(0);
            }

            if (gamepad1.right_stick_y > 0.2) {
                m2.setPower(gamepad1.right_stick_y);
            } else if (gamepad1.right_stick_y < -0.2){
                m3.setPower(gamepad1.right_stick_y);
            } else {
                m2.setPower(0);
                m3.setPower(0);
            }

            //telemetry.addData("Port 0 Power:", m0power);
        }
    }
}