package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Basic(White girl) Drive", group = "TeleOp")

public class BasicDrive extends LinearOpMode {
    Hardware robot = new Hardware();
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            float forward = Math.abs(gamepad1.left_stick_y)>0.1 ? -gamepad1.left_stick_y:0;
            float right = Math.abs(gamepad1.left_stick_x)>0.1 ? gamepad1.left_stick_x:0;
            float clockwise = Math.abs(gamepad1.right_stick_x)>0.1 ? gamepad1.right_stick_x:0;

            clockwise *= 0.1;

            robot.frontLeftMotor.setPower(Range.clip(forward+clockwise+right,-1,1));
            robot.backLeftMotor.setPower(Range.clip(forward-clockwise-right,-1,1));
            robot.frontRightMotor.setPower(Range.clip(forward+clockwise-right,-1,1));
            robot.backRightMotor.setPower(Range.clip(forward-clockwise+right,-1,1));

            robot.waitForTick(40);
        }
    }
}