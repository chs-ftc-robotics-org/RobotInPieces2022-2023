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
        timer.reset();
        while(timer.seconds() < 3 && opModeIsActive()) {
            robot.drivetrain.move(0.3);
            robot.drivetrain.update();
        }
        timer.reset();
        while(timer.seconds() < 1 && opModeIsActive()) {
            robot.slides.lower(0.2);
        }
        robot.slides.lockClaw();
        timer.reset();
        while(timer.seconds() < 5 && opModeIsActive()) {
            robot.slides.raise(0.2);
            robot.drivetrain.move(0.2);
        }
        timer.reset();
        while(timer.seconds() < 1 && opModeIsActive()) {
            robot.slides.lower(0.2);
        }
        robot.slides.unlockClaw();
        timer.reset();
        while(timer.seconds() < 2 && opModeIsActive()) {
            robot.drivetrain.move(-0.2);
        }
        timer.reset();
        while(timer.seconds() < 5 && opModeIsActive()) {
            robot.slides.lower(0.2);
            if(positionToMove == 1) {
                timer.reset();
                robot.drivetrain.strafe(-0.2);
            } else if(positionToMove == 2) {
                //stay there
            } else {
                timer.reset();
                robot.drivetrain.strafe(0.2);
            }
        }

    }
}
