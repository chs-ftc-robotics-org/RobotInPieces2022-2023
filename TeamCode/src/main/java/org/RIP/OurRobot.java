package org.RIP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.RIP.subsystems.*;

public class OurRobot {

    // add stuff here this basically is a class that lets you access all functionality of bot

    public Drivetrain drivetrain = new Drivetrain();
    public ConeWebcam coneWebcam = new ConeWebcam();

    private final Subsystem[] subsystems = {
            drivetrain,
            coneWebcam
    };

    public void initialize(LinearOpMode opMode) {
        for(Subsystem system : subsystems){
                system.initialize(opMode, this);
        }
    }
}