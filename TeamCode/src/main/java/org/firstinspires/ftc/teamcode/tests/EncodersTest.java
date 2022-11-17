package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="Encoders: Test", group="Tests")
public class EncodersTest extends LinearOpMode {

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
            telemetry.addLine("Encoders:");
            telemetry.addData("back_right current position: ", m0.getCurrentPosition());
            telemetry.addData("back_left current position: ", m1.getCurrentPosition());
            telemetry.addData("front_right current position: ", m2.getCurrentPosition());
            telemetry.addData("front_left current position: ", m3.getCurrentPosition());
        }
    }
}