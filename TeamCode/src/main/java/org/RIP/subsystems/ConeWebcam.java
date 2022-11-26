package org.RIP.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.RIP.OurRobot;
import org.RIP.Subsystem;

public class ConeWebcam implements Subsystem {
    @Override
    public boolean active() {
        return false;
    }

    @Override
    public void initialize(LinearOpMode opMode, OurRobot robot, ElapsedTime globalTimer) {

    }

    @Override
    public void tick() {

    }

    @Override
    public void disable() {

    }

    @Override
    public String toString() {
        return "ConeWebcam";
    }
}
