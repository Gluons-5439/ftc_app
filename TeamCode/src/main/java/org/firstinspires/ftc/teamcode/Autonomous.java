package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.hardware.modernrobotics.*;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.util.*;


@Autonomous(name = "CompetitionAuto", group = "Autonomous")


public class Autonomous extends LinearOpMode {
    Hardware robot = new Hardware();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {
            double theta = gyro.getHeading();
            //Creates variable theta which equals robot heading

            //failsafe: switch forward and right
            double forward = Math.abs(gamepad1.left_stick_y) > 0.1 ? -gamepad1.left_stick_y : 0;
            double right = Math.abs(gamepad1.left_stick_x) > 0.1 ? gamepad1.left_stick_x : 0;
            double clockwise = Math.abs(gamepad1.right_stick_x) > 0.1 ? gamepad1.right_stick_x : 0;
            //Variables to set values based on controller input, 0.1 is deadzone

            double temp = forward * Math.cos(Math.toRadians(theta)) - right * Math.sin(Math.toRadians(theta));
            right = forward * Math.sin(theta) + right * Math.cos((theta));
            forward = temp;
            //Math for drive relative to theta

            clockwise *= -0.5;
            //Sets speed when rotating, still needs work

            robot.frontLeftMotor.setPower(Range.clip(forward + clockwise + right, -1, 1));
            robot.backLeftMotor.setPower(Range.clip(forward + clockwise - right, -1, 1));
            robot.frontRightMotor.setPower(Range.clip(forward - clockwise - right, -1, 1));
            robot.backRightMotor.setPower(Range.clip(forward - clockwise + right, -1, 1));
            //Three linear variables intersecting non-linearly for mecanum drive

            float padTwoLeftY = Math.abs(gamepad2.left_stick_y) > 0.2 ? -gamepad2.left_stick_y : 0;
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
