package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Alexander Terry on 11/14/2017.
 */

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Alexander_Test", group = "Autonomous")

public class Alexander_Autonomous_Test extends LinearOpMode {
    Hardware robot = new Hardware();
    AutonomousTools t = new AutonomousTools(robot);

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        t.initPulleyRaiser();
        Thread.sleep(1000);

        t.goForward(0.5);
        Thread.sleep(100);
        t.goStop();

        //INSERT JEWEL CODE HERE

        t.goBackward(0.5);
        Thread.sleep(100);
        t.goStop();
        t.goRight(0.5);
        Thread.sleep(750);
        t.goStop();
    }
}