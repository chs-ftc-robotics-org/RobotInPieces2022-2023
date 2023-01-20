package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.RIP.OurRobot;

@Autonomous(name="Auto: Camera Test", group="Tests")
public class AutoCameraTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        //Get motors from Hardware Map
        OurRobot robot = new OurRobot();
        robot.initialize(this);
        ElapsedTime timer = new ElapsedTime();
        waitForStart();

        timer.reset();
        while (timer.seconds() < 5 && opModeIsActive()) {
            robot.coneWebcam.update();
        }
        int position = robot.coneWebcam.getPositionFromCamera();
        telemetry.addData("Position: ", position);
        telemetry.update();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
