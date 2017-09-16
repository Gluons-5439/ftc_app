package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Linear Driver TeleOp", group = "TeleOp")

public class AManualD extends LinearOpMode {
    Hardware robot = new Hardware();
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            if(gamepad1.left_bumper) throw new NullPointerException();

            robot.frontLeftMotor.setPower(gamepad1.left_stick_y);
            robot.backLeftMotor.setPower(gamepad1.left_stick_y);
            robot.frontRightMotor.setPower(gamepad1.right_stick_y);
            robot.backRightMotor.setPower(gamepad1.right_stick_y);

            if(gamepad1.right_bumper || gamepad2.right_bumper) robot.cannonMotor.setPower(1);
            else robot.cannonMotor.setPower(0);

            if (gamepad1.right_trigger > 0.1 || gamepad2.right_trigger > 0.1) robot.lift.setPower(1.0);
            else if (gamepad1.left_trigger > 0.1 || gamepad2.left_trigger > 0.1) robot.lift.setPower(-1.0);
            else robot.lift.setPower(0);

            robot.waitForTick(40);
        }
    }
}