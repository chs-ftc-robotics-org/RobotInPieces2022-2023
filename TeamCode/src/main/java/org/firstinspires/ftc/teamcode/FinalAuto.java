package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.RIP.OurRobot;

@Autonomous(name="Final Auto")
public class FinalAuto extends LinearOpMode {
    //@Override

    private double sub = 0;

    private double mot1 = 0,mot2 = 0,mot3 = 0,mot4 = 0;
    private double pow;
    private boolean strafe = false;
    private boolean brake = false;


    public void runOpMode() {
        //Get motors from Hardware Map
        OurRobot robot = new OurRobot();
        robot.initialize(this);
        ElapsedTime timer = new ElapsedTime();
        waitForStart();

        while(timer.seconds() < 5){
            robot.coneWebcam.update();
        }
        timer.reset();

        int positionToMove = robot.coneWebcam.getPositionFromCamera();
        while(opModeIsActive()) {
            if (positionToMove == 1) {
                pow = -0.75;
            } else if (positionToMove == 2) {
                pow = 0;
            } else if (positionToMove == 3) {
                pow = 0.75;
            } else {
                telemetry.addLine("No position found");
                telemetry.update();
                brake = true;
            }

            if (!brake)
                sub = timer.seconds();
            else
                sub = -1;

            telemetry.addLine(sub + " seconds have passed");
            telemetry.update();

            if (0 <= sub && sub <= 1.75) {
                System.out.println(sub + " milliseconds have passed");
                robot.drivetrain.move(0.5);

            } else if (sub > 2.0 && sub <= 3.25) {
                robot.drivetrain.strafe(pow);
            } else {
                robot.drivetrain.move(0);
            }

            brake = false;

        }

    }
}
