package org.firstinspires.ftc.teamcode.tests.standalone;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/** Servo Test.
 * The servo on Port 0 of the control hub is controlled by the left stick.
 * The servo on Port 0 of the expansion hub is controlled by the right stick.
 * Each servo updates only once every second.
 */
@TeleOp(name="Servos: Test", group="Tests")
public class ServosTest extends LinearOpMode {


    //When the "INIT" button is pressed:
    @Override
    public void runOpMode() {
        //Get motors from Hardware Map
        Servo c0 = hardwareMap.get(Servo.class, "Test");
        Servo s0 = hardwareMap.get(Servo.class, "claw");
//        Servo test = hardwareMap.getAll
        waitForStart();
        double y = 0;
        double y2 = 0;

        while(opModeIsActive()){
            y = -gamepad2.left_stick_y;

            y2 = -gamepad2.right_stick_y;
            c0.setPosition(y);
            c0.setPosition(y2);

            //telemetry.addData("Port 0 Power:", m0power);
            telemetry.addLine("Control hub port 0 pos:" + String.valueOf(c0.getPosition()));
            telemetry.addLine("Expansion hub port 0 pos: " + String.valueOf(s0.getPosition()));
            telemetry.update();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}