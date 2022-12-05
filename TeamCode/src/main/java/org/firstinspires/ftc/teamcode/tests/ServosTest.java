package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Servos: Test", group="Tests")
public class ServosTest extends LinearOpMode {


    //When the "INIT" button is pressed:
    @Override
    public void runOpMode() {
        //Get motors from Hardware Map
        Servo c0 = hardwareMap.get(Servo.class, "Test");
//        Servo test = hardwareMap.getAll
        waitForStart();

        while(opModeIsActive()){
            c0.setPosition(1);
            //telemetry.addData("Port 0 Power:", m0power);
            telemetry.update();
        }
    }
}