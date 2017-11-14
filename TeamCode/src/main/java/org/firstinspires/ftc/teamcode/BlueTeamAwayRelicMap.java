package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.hardware.modernrobotics.*;
import com.qualcomm.robotcore.util.*;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Drive Straight", group = "Autonomous")


public class BlueTeamAwayRelicMap extends LinearOpMode {
    Hardware robot = new Hardware();
    //AutonomousTools t = new AutonomousTools(robot);
    VuforiaLocalizer vuforia;
    VuforiaTrackables relicTrackables = null;
    VuforiaTrackable relicTemplate = null;
    public void runOpMode() throws InterruptedException{
        //t.initVuforia(vuforia, hardwareMap, relicTrackables, relicTemplate);

        robot.init(hardwareMap);
        waitForStart();

        //24" RIGHT 12" BACKWARDS

        goForward(0.5);
        sleep(2000);
        goStop();
        double pow = 0.5;

        /*
        relicTrackables.activate();

        robot.jewelExtend.setPosition(.5);
        //vuforia business here for sensing the color of the ball
        t.goLeft(pow);
        Thread.sleep(2000);
        t.goForward(pow);
        Thread.sleep(100);
        //let go of claw to place block
        Thread.sleep(100);
        t.goBackward(pow);
        Thread.sleep(100);
        t.goStop();


        //Need to first put jewel servo down between balls to sense it. Then turn right/left depending on which one and back, go forward and place block into cryptobox
        */
    }
    public void goStop()  {
        robot.frontLeftMotor.setPower(0);
        robot.frontRightMotor.setPower(0);
        robot.backLeftMotor.setPower(0);
        robot.backRightMotor.setPower(0);
    }

    public void goForward(double p) {

        robot.frontLeftMotor.setPower(p);
        robot.frontRightMotor.setPower(p);
        robot.backLeftMotor.setPower(-p);
        robot.backRightMotor.setPower(-p);
    }
}