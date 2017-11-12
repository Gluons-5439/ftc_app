package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Hardware;


import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "TestPlatformClaw", group = "TeleOp")

public class TestPlatformClaw extends LinearOpMode {
    Hardware robot = new Hardware();
    //Creates robot object
    ModernRoboticsI2cGyro gyro = null;
    //Declares gyro
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        //Upon initialization maps robot hardware

        gyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro.calibrate();
        //Maps and calibrates gyro heading during initialization
        while (!isStopRequested() && gyro.isCalibrating()) {
            sleep(50);
            idle();
        }
        //Makes sure initialization runs long enough for gyro to finish calibration

        waitForStart();

        gyro.resetZAxisIntegrator();


        while (opModeIsActive()) {
            double theta = gyro.getHeading();
            //Creates variable theta which equals robot heading

            //failsafe: switch forward and right
            double forward = Math.abs(gamepad1.left_stick_y)>0.1 ? -gamepad1.left_stick_y:0;
            double right = Math.abs(gamepad1.left_stick_x)>0.1 ? gamepad1.left_stick_x:0;
            double clockwise = Math.abs(gamepad1.right_stick_x)>0.1 ? gamepad1.right_stick_x:0;
            //Variables to set values based on controller input, 0.1 is deadzone

            double temp = forward*Math.cos(Math.toRadians(theta)) - right*Math.sin(Math.toRadians(theta));
            right = forward*Math.sin(theta) + right*Math.cos((theta));
            forward = temp;
            //Math for drive relative to theta

            clockwise *= -0.5;
            //Sets speed when rotating, still needs work

            robot.frontLeftMotor.setPower(Range.clip(forward+clockwise+right,-1,1));
            robot.backLeftMotor.setPower(Range.clip(forward+clockwise-right,-1,1));
            robot.frontRightMotor.setPower(Range.clip(forward-clockwise-right,-1,1));
            robot.backRightMotor.setPower(Range.clip(forward-clockwise+right,-1,1));
            //Three linear variables intersecting non-linearly for mecanum drive


            if(gamepad1.a) {
                robot.initialPulleyRaiser.setPower(1);
                Thread.sleep(100);
                robot.initialPulleyRaiser.setPower(0);
                robot.bottomClaw.setPosition(0);
             }


            float padTwoLeftY = Math.abs(gamepad2.left_stick_y)>0.2 ? -gamepad2.left_stick_y : 0;
            //Deadzone for lift motors
            robot.liftMotorLeft.setPower(padTwoLeftY);
            robot.liftMotorRight.setPower(padTwoLeftY);
            //Sets power for motors raising and lowering pulley equal to gamepad2 left stick

            telemetry.addData("Heading of Gyro:", theta);
            telemetry.update();
            //Adds gyro heading to telemetry

            robot.waitForTick(40);
            //Stops phone from queuing too many commands and breaking
        }
    }
}