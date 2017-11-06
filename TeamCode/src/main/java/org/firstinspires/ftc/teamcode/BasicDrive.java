package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Basic Drive", group = "TeleOp")

public class BasicDrive extends LinearOpMode {
    Hardware robot = new Hardware();
    ModernRoboticsI2cGyro gyro = null;
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        gyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro.calibrate();
        while (!isStopRequested() && gyro.isCalibrating()) {
            sleep(50);
            idle();
        }

        waitForStart();

        gyro.resetZAxisIntegrator();


        while (opModeIsActive()) {
            double theta = gyro.getHeading();


            double right = Math.abs(gamepad1.left_stick_y)>0.1 ? -gamepad1.left_stick_y:0;
            double forward = Math.abs(gamepad1.left_stick_x)>0.1 ? gamepad1.left_stick_x:0;
            double clockwise = Math.abs(gamepad1.right_stick_x)>0.1 ? gamepad1.right_stick_x:0;
            //Variables to set values based on controller input, 0.1 is deadzone

            double temp = forward*Math.cos(Math.toRadians(theta)) - right*Math.sin(Math.toRadians(theta));
            right = forward*Math.sin(theta) + right*Math.cos((theta));
            forward = temp;

            clockwise *= -0.5;
            //Still screwy

            robot.frontLeftMotor.setPower(Range.clip(forward+clockwise+right,-1,1));
            robot.backLeftMotor.setPower(Range.clip(forward-clockwise-right,-1,1));
            robot.frontRightMotor.setPower(Range.clip(forward+clockwise-right,-1,1));
            robot.backRightMotor.setPower(Range.clip(forward-clockwise+right,-1,1));
            //Three linear variables intersecting non-linearly

            float padTwoLeftY = Math.abs(gamepad2.left_stick_y)>0.2 ? -gamepad2.left_stick_y : 0;
            //Deadzone for lift motors
            robot.liftMotorLeft.setPower(padTwoLeftY);
            robot.liftMotorRight.setPower(padTwoLeftY);
            //Sets power for motors raising and lowering pulley equal to gamepad2 left stick


            //Adds gyro heading to gyros gyro headings telemetry gyro telemetry heading
            telemetry.addData("Heading of Gyro:", theta);
            telemetry.update();

            robot.waitForTick(40);
            //Stops phone from queuing too many commands and breaking
        }
    }
}