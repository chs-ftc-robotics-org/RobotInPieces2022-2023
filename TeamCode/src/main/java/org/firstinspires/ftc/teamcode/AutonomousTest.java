package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomous: Test", group="Tests")
public class AutonomousTest extends LinearOpMode {

    //Declaring motors here
    private DcMotor motor = null;
    private DcMotor motor2 = null;
    private DcMotor motor3 = null;
    private DcMotor motor4 = null;

    //When the "INIT" button is pressed:
    @Override
    public void runOpMode() {
        //Get motors from Hardware Map
        motor = hardwareMap.get(DcMotor.class, "front_right");
        motor2 = hardwareMap.get(DcMotor.class, "front_left");
        motor3 = hardwareMap.get(DcMotor.class, "back_right");
        motor4 = hardwareMap.get(DcMotor.class, "back_left");

        //An example of a timer:
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        double seconds = timer.milliseconds();

        //Wait here until the "START" button is pressed
        waitForStart();

        double start = 0;
        double end;
        double x = 0;
        int add = 0;
        //Loop this until the "STOP" button is pressed
        while (opModeIsActive()) {
            if (add == 0)
                start = timer.milliseconds();

            end = timer.milliseconds();
            x  = end - start;
            if (8000 >= x && x >= 4000) {
                System.out.println(x + " milliseconds have passed");
                motor3.setPower(.1);
                motor4.setPower(.1);

            }
            add++;
        }
    }
}