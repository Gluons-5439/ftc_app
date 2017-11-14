package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.hardware.modernrobotics.*;
import com.qualcomm.robotcore.util.*;


//@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "RedTeamByRelic", group = "Autonomous")


public class RedTeamByRelicMap extends LinearOpMode {
    Hardware robot = new Hardware();
    AutonomousTools t = new AutonomousTools(robot);

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();

        t.goForward(0.5);
        sleep(2000);
        t.goStop();

        // 36" RIGHT
/*
// ALL TIMES ARE THEORETICAL!! NEED TESTING FIRST!

        t.goRight(); // depending on which side the claw is this MUST be adjusted
        Thread.sleep(250);
        t.goStop();
        robot.jewelExtend.setPosition(.5);

        //vuforia business here for sensing the color of the ball
        //strafe direction depending on ball
        robot.jewelExtend.setPosition(0);
        t.goLeft();
        Thread.sleep(2000);
        //goForward();
        //Thread.sleep(100);
        //let go of claw to place block
        //Thread.sleep(100);
        //goBackward();
        //Thread.sleep(100);
        t.goStop();


        //Need to first put jewel servo down between balls to sense it. Then turn right/left depending on which one and back, go forward and place block into cryptobox
*/
    }

}