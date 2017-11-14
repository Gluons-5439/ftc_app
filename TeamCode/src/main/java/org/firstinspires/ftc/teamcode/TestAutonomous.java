package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.hardware.modernrobotics.*;
import com.qualcomm.robotcore.util.*;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "BlueTeamAwayRelic", group = "Autonomous")


public class TestAutonomous extends LinearOpMode {
    Hardware robot = new Hardware();
    AutonomousTools t = new AutonomousTools(robot);
    VuforiaLocalizer vuforia;
    VuforiaTrackables relicTrackables = null;
    VuforiaTrackable relicTemplate = null;
    public void runOpMode() throws InterruptedException {
//        t.initVuforia(vuforia, hardwareMap, relicTrackables, relicTemplate);

        robot.init(hardwareMap);
        waitForStart();


    }
}