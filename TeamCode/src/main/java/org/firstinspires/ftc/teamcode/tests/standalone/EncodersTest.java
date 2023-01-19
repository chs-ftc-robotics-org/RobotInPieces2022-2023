package org.firstinspires.ftc.teamcode.tests.standalone;

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
        DcMotor s1 = hardwareMap.get(DcMotor.class, "slide_right");
        DcMotor s0 = hardwareMap.get(DcMotor.class, "slide_left");
        DcMotor m3 = hardwareMap.get(DcMotor.class, "front_left");
        DcMotor m2 = hardwareMap.get(DcMotor.class, "front_right");
        DcMotor m1 = hardwareMap.get(DcMotor.class, "back_left");
        DcMotor m0 = hardwareMap.get(DcMotor.class, "back_right");
        //telemetry.addLine("reached before start");
        waitForStart();
        //telemetry.addLine("reached here");

        while(opModeIsActive()){
            telemetry.addLine("Encoders:");
            telemetry.addData("back_right current position: ", m0.getCurrentPosition());
            telemetry.addData("back_left current position: ", m1.getCurrentPosition());
            telemetry.addData("front_right current position: ", m2.getCurrentPosition());
            telemetry.addData("front_left current position: ", m3.getCurrentPosition());
            telemetry.addData("slide_right current position: ", s1.getCurrentPosition());
            telemetry.addData("slide_left current position: ", s0.getCurrentPosition());
            telemetry.update();
        }
    }
}