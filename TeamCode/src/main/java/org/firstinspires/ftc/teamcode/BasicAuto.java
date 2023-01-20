package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.RIP.OurRobot;

@Autonomous(name="Basic Auto")
public class BasicAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
        //Get motors from Hardware Map
        OurRobot robot = new OurRobot();
        robot.initialize(this);
        ElapsedTime timer = new ElapsedTime();
        waitForStart();

        timer.reset();
        double speed = 0;
        while(timer.seconds() < 5 && opModeIsActive()) {
            if(speed < 0.5)
                speed = speed + 0.04;
            robot.drivetrain.move(speed);
        }

    }
}
