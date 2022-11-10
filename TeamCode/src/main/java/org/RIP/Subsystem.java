package org.RIP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public interface Subsystem {
    boolean active();
    void initialize(LinearOpMode opMode, OurRobot robot);
    void tick();
    void disable();
}