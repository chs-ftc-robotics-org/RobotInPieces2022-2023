package org.RIP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public interface Subsystem {
    boolean active();
    void initialize(LinearOpMode opMode, OurRobot robot, ElapsedTime globalTimer);
    void tick();
    void disable();
}