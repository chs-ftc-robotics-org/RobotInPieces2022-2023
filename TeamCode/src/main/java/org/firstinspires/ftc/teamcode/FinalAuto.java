package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.RIP.OurRobot;

@Autonomous(name="Final Auto")
public class FinalAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
        //Get motors from Hardware Map
        OurRobot robot = new OurRobot();
        robot.initialize(this);
        ElapsedTime timer = new ElapsedTime();
        waitForStart();

        timer.reset();
        while(timer.seconds() < 5 && opModeIsActive()){
            robot.coneWebcam.update();
        }
        int positionToMove = robot.coneWebcam.getPositionFromCamera();
        if(positionToMove == 1) {
            while(opModeIsActive()) {
                robot.drivetrain.move(0.3, 5000);
                robot.drivetrain.update();
            }
        } else if(positionToMove == 2) {

        } else {

        }
    }
}
