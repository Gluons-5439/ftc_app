package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.hardware.modernrobotics.*;
import com.qualcomm.robotcore.util.*;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "RedTeamByRelic", group = "Autonomous")


public class RedTeamByRelicMap extends LinearOpMode {
    Hardware robot = new Hardware();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();

        // 36" RIGHT

// ALL TIMES ARE THEORETICAL!! NEED TESTING FIRST!

        goRight(); // depending on which side the claw is this MUST be adjusted
        Thread.sleep(250);
        goStop();
        robot.jewelExtend.setPosition(.5);

        //vuforia business here for sensing the color of the ball
        //strafe direction depending on ball
        robot.jewelExtend.setPosition(0);
        goLeft();
        Thread.sleep(2000);
        //goForward();
        //Thread.sleep(100);
        //let go of claw to place block
        //Thread.sleep(100);
        //goBackward();
        //Thread.sleep(100);
        goStop();


        //Need to first put jewel servo down between balls to sense it. Then turn right/left depending on which one and back, go forward and place block into cryptobox

    }

    public void goStop()  {
        robot.frontLeftMotor.setPower(0);
        robot.frontRightMotor.setPower(0);
        robot.backLeftMotor.setPower(0);
        robot.backRightMotor.setPower(0);
    }

    public void goForward() {

        robot.frontLeftMotor.setPower(1);
        robot.frontRightMotor.setPower(1);
        robot.backLeftMotor.setPower(1);
        robot.backRightMotor.setPower(1);
    }

    public void goBackward() {

        robot.frontLeftMotor.setPower(-1);
        robot.frontRightMotor.setPower(-1);
        robot.backLeftMotor.setPower(-1);
        robot.backRightMotor.setPower(-1);

    }

    public void goRight() {
        robot.frontLeftMotor.setPower(-1);
        robot.frontRightMotor.setPower(1);
        robot.backLeftMotor.setPower(-1);
        robot.backRightMotor.setPower(1);

    }

    public void goLeft() {
        robot.frontLeftMotor.setPower(1);
        robot.frontRightMotor.setPower(-1);
        robot.backLeftMotor.setPower(1);
        robot.backRightMotor.setPower(-1);

    }
    public void moveVector(double degree, double power){
        double clockwise = 0;
        double forward = 0;
        double right = 0;

        double rightDegree = Math.toDegrees(Math.sin(degree)*power);
        double forwardDegree = Math.toDegrees(Math.cos(degree)*power);
        right = Math.toRadians(degree);
        forward = Math.toRadians(degree);


            //clockwise *= -0.5;
        robot.frontLeftMotor.setPower(Range.clip(forward+clockwise+right,-1,1));
        robot.backLeftMotor.setPower(Range.clip(forward+clockwise-right,-1,1));
        robot.frontRightMotor.setPower(Range.clip(forward-clockwise-right,-1,1));
        robot.backRightMotor.setPower(Range.clip(forward-clockwise+right,-1,1));

    }
}