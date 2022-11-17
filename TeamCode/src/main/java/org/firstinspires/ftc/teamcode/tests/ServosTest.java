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
        Servo c3 = hardwareMap.get(Servo.class, "front_left");
        Servo c2 = hardwareMap.get(Servo.class, "front_right");
        Servo c1 = hardwareMap.get(Servo.class, "back_left");
        Servo c0 = hardwareMap.get(Servo.class, "back_right");
        waitForStart();

        while(opModeIsActive()){
            

            //telemetry.addData("Port 0 Power:", m0power);
        }
    }
}