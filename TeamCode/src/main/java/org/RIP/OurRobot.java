package org.RIP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.RIP.subsystems.*;

public class OurRobot {

    // add stuff here this basically is a class that lets you access all functionality of bot

    public Drivetrain drivetrain = new Drivetrain();
    public ConeWebcam coneWebcam = new ConeWebcam();
    public ElapsedTime globalTimer = new ElapsedTime();

    private final Subsystem[] subsystems = {
            drivetrain,
            coneWebcam
    };

    public void initialize(LinearOpMode opMode) {
        globalTimer.reset();
        for(Subsystem system : subsystems){
                system.initialize(opMode, this, globalTimer);
        }
    }
}