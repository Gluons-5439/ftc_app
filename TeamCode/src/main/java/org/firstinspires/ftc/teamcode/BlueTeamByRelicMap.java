package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.hardware.modernrobotics.*;
import com.qualcomm.robotcore.util.*;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "BlueTeamByRelic", group = "Autonomous")


public class BlueTeamByRelicMap extends LinearOpMode {
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

       int column = 1;
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if(vuMark != RelicRecoveryVuMark.UNKNOWN){
            if(vuMark == RelicRecoveryVuMark.LEFT){
                column = 0;
            }else if(vuMark == RelicRecoveryVuMark.RIGHT){
                column = 2;
            }else{
                column = 1;
            }
        }



        // 36" RIGHT

        t.initPulleyRaiser();



        robot.liftMotorRight.setPower(-.5);
        robot.liftMotorLeft.setPower(-.5); //Lowers platform as pulleys raise so it doesnt get stuck up top
        Thread.sleep(5);
//        robot.bottomClaw.
//        robot.topClaw.setPosition(0); //gets claws out of the way
        Thread.sleep(245);
        robot.initialPulleyRaiser.setPower(0);  //stops clips and platform drops
        //ROBOT SHOULD BE FULLY EXPANDED AT THIS POINT

        t.goRight(); //Switch depending on where jewelExtend is
        Thread.sleep(200);
        t.goStop();
        robot.jewelExtend.setPosition(.5);
        //vuforia business here for sensing the color of the ball
        robot.jewelExtend.setPosition(0);
        t.goRight();
        Thread.sleep(2000);
        //if then statement to turn left/right and back to pop off jewel
    //    if(column == 0) {
     //       Thread.sleep(1000);
    //    }else if (column == 1) {
     //       Thread.sleep(1100);
    //    }else {
    //        Thread.sleep(1200);
    //    }

      //  goForward();
     //   Thread.sleep(100);
     //   robot.bottomClaw.setPosition(.5);
     //   Thread.sleep(100);
     //   goBackward();
     //   Thread.sleep(100);
        t.goStop();


        //Need to first put jewel servo down between balls to sense it. Then turn right/left depending on which one and back, go forward and place block into cryptobox

    }
}