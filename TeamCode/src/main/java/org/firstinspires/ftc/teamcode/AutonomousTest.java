package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomous: Test", group="Tests")
public class AutonomousTest extends LinearOpMode {

    //Declaring motors here
    private DcMotor motor = null;

    //When the "INIT" button is pressed:
    @Override
    public void runOpMode() {
        //Get motors from Hardware Map
        motor = hardwareMap.get(DcMotor.class, "motor");

        //An example of a timer:
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        double seconds = timer.milliseconds();

        //Wait here until the "START" button is pressed
        waitForStart();

        //Loop this until the "STOP" button is pressed
        while (opModeIsActive()) {

        }
    }
}