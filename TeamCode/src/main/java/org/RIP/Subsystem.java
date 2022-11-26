package org.RIP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.io.IOException;

public interface Subsystem {
    boolean isActive = false;
    boolean active();
    void initialize(LinearOpMode opMode, OurRobot robot, ElapsedTime globalTimer) throws IOException;
    void tick();
    void stop();
    void disable();
}