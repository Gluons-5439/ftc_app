package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.hardware.modernrobotics.*;
import com.qualcomm.robotcore.util.*;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "BlueTeamByRelic", group = "Autonomous")


public class BlueTeamByRelicMap extends LinearOpMode {
    Hardware robot = new Hardware();
    public void runOpMode() throws InterruptedException{

        robot.init(hardwareMap);
        waitForStart();

        goForward(0.5);
        sleep(750);
        goStop();
        goLeft(0.5);
        sleep(250);
        goStop();
        goForward(0.5);
        sleep(100);
        goStop();
        double pow = 0.5;
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

    public void goLeft(double p) {
        robot.frontLeftMotor.setPower(p);
        robot.frontRightMotor.setPower(-p);
        robot.backLeftMotor.setPower(p);
        robot.backRightMotor.setPower(-p);

    }
}