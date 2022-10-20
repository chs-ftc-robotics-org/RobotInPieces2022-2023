package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomous: Test", group="Tests")
public class AutonomousTest extends LinearOpMode {

    //Declaring motors here

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

        //An example of a timer:
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        double seconds = timer.milliseconds();

        //Wait here until the "START" button is pressed
        waitForStart();

        double start = 0;
        double end;
        double sub = 0;

        double x = 0.1;

        int add = 0;
        //Loop this until the "STOP" button is pressed
        while (opModeIsActive()) {
            if (add == 0)
                start = timer.milliseconds();

            end = timer.milliseconds();
            sub  = end - start;
            frontLeft.setPower(x);
            backLeft.setPower(x);
            frontRight.setPower(x);
            backRight.setPower(x);

            if (10000 >= sub && sub >= 4000) {
                System.out.println(x + " milliseconds have passed");
                x = -(0.2);
            } else if (sub > 10000){
                x = 0;
            }
            add++;
        }
    }
}