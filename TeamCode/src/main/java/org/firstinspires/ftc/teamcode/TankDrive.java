package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Tank Drive", group = "TeleOp")

public class TankDrive extends LinearOpMode{
    Hardware robot = new Hardware();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            double left;
            double right;

            // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
            left = -gamepad1.left_stick_y;
            right = -gamepad1.right_stick_y;

            robot.frontLeftMotor.setPower(left);
            robot.backLeftMotor.setPower(-left);
            robot.frontRightMotor.setPower(right);
            robot.backRightMotor.setPower(-right);
        }
    }
}