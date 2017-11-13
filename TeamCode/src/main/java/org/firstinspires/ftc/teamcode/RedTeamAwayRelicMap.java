package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.hardware.modernrobotics.*;
import com.qualcomm.robotcore.util.*;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "RedTeamAwayRelic", group = "Autonomous")


public class RedTeamAwayRelicMap extends LinearOpMode {
    Hardware robot = new Hardware();
    AutonomousTools t = new AutonomousTools(robot);
    VuforiaLocalizer vuforia;
    VuforiaTrackables relicTrackables = null;
    VuforiaTrackable relicTemplate = null;
    public void runOpMode() throws InterruptedException {
        t.initVuforia(vuforia, hardwareMap, relicTrackables, relicTemplate);

        robot.init(hardwareMap);
        waitForStart();


        relicTrackables.activate();

        //24" LEFT, 12" BACKWARD

        robot.jewelExtend.setPosition(.5);
        //vuforia business here for sensing the color of the ball
        t.goLeft();
        Thread.sleep(2000);
        t.goForward();
        Thread.sleep(100);
        //let go of claw to place block
        Thread.sleep(100);
        t.goBackward();
        Thread.sleep(100);
        t.goStop();


        //Need to first put jewel servo down between balls to sense it. Then turn right/left depending on which one and back, go forward and place block into cryptobox

    }

}