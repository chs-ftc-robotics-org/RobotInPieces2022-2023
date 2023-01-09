package org.RIP.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.RIP.Subsystem;

import java.io.IOException;



public class Slides extends Subsystem {
    DcMotor slideMotor;
    TouchSensor slideSensor;
    ElapsedTime globalTimer;
    LinearOpMode opMode;
    @Override
    public void initialize(LinearOpMode opMode, ElapsedTime globalTimer) throws IOException {
        this.opMode = opMode;
        this.globalTimer = globalTimer;
        slideMotor = opMode.hardwareMap.get(DcMotor.class, "slide_motor");
        slideSensor = opMode.hardwareMap.get(TouchSensor.class, "slide_sensor");
        ElapsedTime timeout = new ElapsedTime();
        timeout.reset();
        //move slides down
        while(!slideSensor.isPressed() && timeout.seconds() < 6) {
            slideMotor.setPower(0.3);
        }
        int startPos = slideMotor.getCurrentPosition();
    }
    @Override
    public void disable() {

    }

    @Override
    public void update() {
        opMode.telemetry.addData("pressed: ", slideSensor.isPressed());
    }
}
